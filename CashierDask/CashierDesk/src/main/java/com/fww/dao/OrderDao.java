package com.fww.dao;

import com.fww.entity.Goods;
import com.fww.entity.Items;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends BaseDao {
    public List<Items> quaryAllItmes() {
        List<Items> itemsList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = this.getConnection(true);
            String sql = "select * from order_item";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Items items = this.getItems(rs);
                if (items != null) {
                    itemsList.add(items);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemsList;

    }
    public Items getItems(ResultSet resultSet) {
       Items items = new Items();
        try {
            items.setId(resultSet.getInt("id"));
            items.setOrderId(resultSet.getInt("order_id"));
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
}
