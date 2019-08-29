package com.fww.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Goods {
    private Integer id;
    private String goodsName;
    private String goodsIntroduce;
    private String goodsUnit;
    private Integer goodsStock;
    private Double price;
    private Integer discount;
}
