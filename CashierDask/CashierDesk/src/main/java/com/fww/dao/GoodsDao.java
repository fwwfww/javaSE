package com.fww.dao;

import com.fww.entity.Goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GoodsDao extends BaseDao {
    public List<Goods> goodsBrowse() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Goods> goodsList = new ArrayList<>();
        try {
            conn = this.getConnection(true);
            String sql = "select * from goods";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Goods goods = this.getGoods(rs);
                if (goods != null) {
                    goodsList.add(goods);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsList;
    }

    public Goods getGoods(ResultSet resultSet) {
        Goods goods = new Goods();
        try {
            goods.setId(resultSet.getInt("id"));
            goods.setGoodsName(resultSet.getString("name"));
            goods.setGoodsIntroduce(resultSet.getString("introduce"));
            goods.setGoodsStock(resultSet.getInt("stock"));
            goods.setGoodsUnit(resultSet.getString("unit"));
            goods.setPrice(resultSet.getDouble("price"));
            goods.setDiscount(resultSet.getInt("discount"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    public boolean goodsPutUp(Goods goods) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean effect = false;
        try {
            conn = this.getConnection(true);
            String sql = "insert into goods(name,introduce,stock,unit,price,discount) values(?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, goods.getGoodsName());
            ps.setString(2, goods.getGoodsIntroduce());
            ps.setInt(3, goods.getGoodsStock());
            ps.setString(4, goods.getGoodsUnit());
            ps.setDouble(5, goods.getPrice());
            ps.setInt(6, goods.getDiscount());
            int ret = ps.executeUpdate();
            if (ret > 0) {
                effect = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResource(conn, ps, rs);
        }
        return effect;
    }

    public Goods findGoodsById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Goods goods = null;
        try {
            conn = this.getConnection(true);
            String sql = "select * from goods where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                goods = getGoods(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    public boolean soldOut(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean effect = false;
        try {
            conn = this.getConnection(true);
            String sql = "delete from goods where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int ret = ps.executeUpdate();
            if (ret > 0) {
                effect = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return effect;
    }

    public boolean goodsUpData(Goods goods1) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean effect = false;
        try {
            conn = this.getConnection(true);
            String sql = "update goods set name=?,introduce=?,stock=?,unit=?,price=?,discount=? where id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, goods1.getGoodsName());
            ps.setString(2, goods1.getGoodsIntroduce());
            ps.setInt(3, goods1.getGoodsStock());
            ps.setString(4, goods1.getGoodsUnit());
            ps.setDouble(5, goods1.getPrice());
            ps.setInt(6, goods1.getDiscount());
            int ret = ps.executeUpdate();
            if (ret > 0) {
                effect = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResource(conn, ps, rs);
        }
        return effect;

    }
}