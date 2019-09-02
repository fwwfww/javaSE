package com.fww.dao;

import com.fww.common.OrderStatus;
import com.fww.entity.Goods;
import com.fww.entity.Items;
import com.fww.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends BaseDao {
    public List<Order> quaryAllOrder(int id) {
        List<Order> orderList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = this.getConnection(true);
            String sql = this.getSql("@quary_order_by_account");
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            Order order = null;
            while (rs.next()) {
              if(order == null){
                  order = new Order();
                  this.getOrder(order,rs);
                  orderList.add(order);
              }
                //拿到了当前的orderID
                String orderId = rs.getString("order_id");
                //只有当订单信息不同的时候，才生成一个订单
                if(!orderId.equals(order.getId())){
                    order = new Order();
                    this.getOrder(order,rs);
                    orderList.add(order);
              }
                Items items = this.getItems(rs);
                order.itemsList.add(items);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;

    }

    private void getOrder(Order order, ResultSet rs) {
        try {
            order.setId(rs.getString("id"));
            order.setAccountId(rs.getInt("account_id"));
            order.setAccountName(rs.getString("account_name"));
            order.setCreatTime(rs.getTimestamp("create_time").toLocalDateTime());
            Timestamp finishTime = rs.getTimestamp("finish_time");
            if (finishTime != null) {
                order.setFinishTime(finishTime.toLocalDateTime());
            }
            order.setStatus(OrderStatus.getOrderStatus(rs.getInt("order_status")));
            order.setTotalMoney(rs.getDouble("total_money"));
            order.setActualMoney(rs.getDouble("actual_amount"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Items getItems(ResultSet resultSet) {
        Items items = new Items();
        try {
            items.setId(resultSet.getInt("id"));
            items.setOrderId(resultSet.getString("order_id"));
            items.setGoodsId(resultSet.getInt("goods_id"));
            items.setGoodsName(resultSet.getString("goods_name"));
            items.setGoodsIntroduce(resultSet.getString("goods_introduce"));
            items.setGoodsNumber(resultSet.getInt("stock"));
            items.setGoodsUnit(resultSet.getString("unit"));
            items.setGoodsPrice(resultSet.getDouble("price"));
            items.setDiscount(resultSet.getInt("discount"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public boolean commitOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.getConnection(false);
            String sql = "insert into `order` (id, account_id, create_time, finish_time, actual_amount, total_money, order_status,account_name) values (?,?,?,?,?,?,?,?)";
            String insertOrderItemSql="insert into order_item(order_id, goods_id, goods_name," +
                    "goods_introduce, goods_num, goods_unit, goods_price, goods_discount) values (?,?,?,?,?,?,?,?)";
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,order.getId());
            preparedStatement.setInt(2,order.getAccountId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(order.getCreatTime()));
            preparedStatement.setTimestamp(4, order.getFinishTime() == null?null:Timestamp.valueOf(order.getFinishTime()));
            preparedStatement.setDouble(5,order.getActualMoney());
            preparedStatement.setDouble(6,order.getTotalMoney());
            preparedStatement.setInt(7, order.getStatus().getFlag());
            preparedStatement.setString(8,order.getAccountName());
            if(preparedStatement.executeUpdate() == 0){
                throw new RuntimeException("插入订单失败！");
            }
            preparedStatement=connection.prepareStatement(insertOrderItemSql);
            for (Items items:order.itemsList) {
               preparedStatement.setString(1,order.getId());
               preparedStatement.setInt(2,items.getGoodsId());
               preparedStatement.setString(3,items.getGoodsName());
               preparedStatement.setString(4,items.getGoodsIntroduce());
               preparedStatement.setInt(5,items.getGoodsNumber());
               preparedStatement.setString(6,items.getGoodsUnit());
               preparedStatement.setDouble(7,items.getGoodsPrice());
               preparedStatement.setInt(8,items.getDiscount());

               preparedStatement.addBatch();
            }
            int ret2[] = preparedStatement.executeBatch();
            for (int i: ret2) {
                if(i == 0){
                    throw new RuntimeException("插入订单项失败！");
                }
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if(connection != null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return  false;
        }
        finally {
            closeResource(connection,preparedStatement,resultSet);
        }
        return true;
    }
}
