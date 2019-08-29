package com.fww.common;

public enum  OrderStatus {
    UNPID(1,"未支付"),PAID(1,"已支付");
    private int flag;
    private String status;

    OrderStatus(int flag, String status) {
        this.flag = flag;
        this.status = status;
    }
    public OrderStatus getOrderStatus(int flag){
        for (OrderStatus orderStatus: values()) {
            if(orderStatus.flag == flag){
                return orderStatus;
            }
        }
        throw new RuntimeException("OrderStatus"+flag+"is not found!");
    }
}
