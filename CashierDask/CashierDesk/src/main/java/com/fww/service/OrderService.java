package com.fww.service;

import com.fww.dao.OrderDao;
import com.fww.entity.Goods;
import com.fww.entity.Items;
import com.fww.entity.Order;

import java.util.List;

public class OrderService {
    public OrderDao orderDao;
    public OrderService(){
        this.orderDao = new OrderDao();
    }
    public List<Order> quaryAllOrder(int id) {
        return this.orderDao.quaryAllOrder(id);
    }


    public boolean commitOrder(Order order) {
        return this.orderDao.commitOrder(order);
    }
}
