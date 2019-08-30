package com.fww.utils.command;

import com.fww.service.*;

public abstract class AbstractCommand implements Command{
    private AccountService accountService;
    private GoodsService goodsService;
    private OrderService orderService;
    public AbstractCommand(){
        accountService = new AccountServiceImpl();
        goodsService = new GoodsServiceImpl();
        orderService = new OrderServiceImpl();
    }
    public void outPut(Object object){
        System.out.println(object);
    }
}
