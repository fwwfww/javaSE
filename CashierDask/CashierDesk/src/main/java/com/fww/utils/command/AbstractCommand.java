package com.fww.utils.command;

import com.fww.service.*;

public abstract class AbstractCommand implements Command{
    public static AccountService accountService;
    public static GoodsService goodsService;
    public OrderService orderService;
    public AbstractCommand(){
        accountService = new AccountService();
        goodsService = new GoodsService();
        orderService = new OrderService();
    }
    public void outPut(Object object){
        System.out.println(object);
    }
}
