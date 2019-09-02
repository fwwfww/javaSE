package com.fww.common;

import lombok.Getter;

@Getter
public enum  OrderStatus {
    UNPID(1,"未支付"),PAID(1,"已支付");
    public int flag;
    public String status;

    OrderStatus(int flag, String status) {
        this.flag = flag;
        this.status = status;
    }
    public static OrderStatus getOrderStatus(int flag){
        for (OrderStatus orderStatus: values()) {
            if(orderStatus.flag == flag){
                return orderStatus;
            }
        }
        throw new RuntimeException("OrderStatus"+flag+"is not found!");
    }
}
