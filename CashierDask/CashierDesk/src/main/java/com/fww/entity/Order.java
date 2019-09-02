package com.fww.entity;

import com.fww.common.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Order {
    private String id;
    private Integer accountId;
    private String accountName;
    private LocalDateTime creatTime;
    private LocalDateTime finishTime;
    private Double totalMoney;
    private Double actualMoney;
    private OrderStatus status;
    public List<Items> itemsList = new ArrayList<>();
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("【订单信息】*************************************").append("\n");
        sb.append("\t").append("【用户名称】：").append(this.getAccountName()).append("\n");
        sb.append("\t").append("【订单编号】：").append(this.getId()).append("\n");
        sb.append("\t").append("【订单状态】：").append(this.getStatus()).append("\n");
        sb.append("\t").append("【创建时间】：").append(this.timeToString(this.getCreatTime())).append("\n");
        if (this.getStatus() == OrderStatus.PAID) {
            sb.append("\t").append("【完成时间】：")
                    .append(this.timeToString(this.getFinishTime())).append("\n");
        }
        sb.append("【订单明细】*************************************").append("\n");
        sb.append("\t编号    名称     数量     单位     单价（元）").append("\n");
        int i = 0;
        for (Items orderItem : this.getItemsList()) {
            sb.append("\t").append(++i).append(".    ")
                    .append(orderItem.getGoodsName()).append("     ")
                    .append(orderItem.getGoodsNumber()).append("     ")
                    .append(orderItem.getGoodsUnit()).append("     ")
                    .append(this.moneyToString(orderItem.getGoodsPrice())).append("     ")
                    .append("\n");
        }
        sb.append("【订单金额】*************************************").append("\n");
        sb.append("\t").append("【总金额】：").append(this.moneyToString(this.getTotalMoney()))
                .append(" 元 ").append("\n");
        sb.append("\t").append("【优惠金额】：").append(this.moneyToString(this.getDiscount()))
                .append(" 元 ").append("\n");
        sb.append("\t").append("【应支付金额】：").append(this.moneyToString(this.getActualMoney()))
                .append(" 元 ").append("\n");
        return sb.toString();
    }

    private String moneyToString(Double money) {
        return String.format("%.2f", money);
    }

    private String timeToString(LocalDateTime time) {
        return DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(time);
    }

    //优惠
    public Double getDiscount() {
        return this.getTotalMoney() - this.getActualMoney();
    }

}
