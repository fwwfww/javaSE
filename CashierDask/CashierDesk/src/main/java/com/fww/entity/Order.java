package com.fww.entity;

import com.fww.common.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Order {
    private Integer id;
    private Integer accountId;
    private String accountName;
    private LocalDateTime creatTime;
    private LocalDateTime finishTime;
    private Double totalMoney;
    private Double actualMoney;
    private OrderStatus status;
}
