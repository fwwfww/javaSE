package com.fww.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class Items {
    private Integer id;
    private String orderId;
    private Integer goodsId;
    private String goodsName;
    private String goodsIntroduce;
    private String goodsUnit;
    private Integer goodsNumber;
    private Double goodsPrice;
    private Integer discount;
}
