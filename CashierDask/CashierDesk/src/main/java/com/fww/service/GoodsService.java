package com.fww.service;

import com.fww.dao.GoodsDao;
import com.fww.entity.Goods;

import java.util.List;

public class GoodsService {
    private GoodsDao goodsDao;
    public GoodsService(){
        this.goodsDao = new GoodsDao();
    }
    public List<Goods> goodsBrowse() {
        return goodsDao.goodsBrowse();
    }

    public boolean goodsPutUp(Goods goods) {
        return this.goodsDao.goodsPutUp(goods);
    }

    public Goods findGoodsById(int id) {
        return this.goodsDao.findGoodsById(id);
    }

    public boolean soldOut(int id) {
        return this.goodsDao.soldOut(id);
    }

    public boolean goodsUpData(Goods goods1) {
        return this.goodsDao.goodsUpData(goods1);
    }
}
