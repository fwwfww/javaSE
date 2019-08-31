package com.fww.service;

import com.fww.dao.OrderDao;
import com.fww.entity.Items;

import java.util.List;

public class OrderService {
    public OrderDao orderDao;
    public OrderService(){
        this.orderDao = new OrderDao();
    }
    public List<Items> quaryAllItems() {
        return this.orderDao.quaryAllItmes();

    }
}
