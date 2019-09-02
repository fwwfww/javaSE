package com.fww.utils.command.order;

import com.fww.common.OrderStatus;
import com.fww.entity.Goods;
import com.fww.entity.Items;
import com.fww.entity.Order;
import com.fww.utils.annotation.CommandMeta;
import com.fww.utils.annotation.CustomerCommand;
import com.fww.utils.command.AbstractCommand;
import com.fww.utils.command.Subject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CustomerCommand
@CommandMeta(
        comm = "ZFDD",
        name = "支付订单",
        group = "订单信息"
)
public class OrderPaidCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        outPut("支付订单");
        outPut("请输入你想要买的商品id以及数量：形式如：1-8，2-4");
        String stringStr = scanner.next();
        String[] goodsStr = stringStr.split(",");
        List<Goods> goodsList = new ArrayList<>();
        for (String str:goodsStr) {
         String[] goodsAndNum = str.split("-");
         Goods goods =this.goodsService.findGoodsById(Integer.parseInt(goodsAndNum[0]));
         goods.setBuyNumber(Integer.parseInt(goodsAndNum[1]));
         goodsList.add(goods);
        }
        Order order = new Order();
        order.setId(String.valueOf(System.currentTimeMillis()));
        order.setAccountId(subject.getAccount().getId());
        order.setAccountName(subject.getAccount().getName());
        order.setCreatTime(LocalDateTime.now());
        Double totalMoney = 0.0;
        Double actuallyMoney = 0.0;
        for (Goods g : goodsList) {
            Items items = new Items();
            items.setGoodsId(g.getId());
            items.setGoodsName(g.getGoodsName());
            items.setGoodsIntroduce(g.getGoodsIntroduce());
            items.setGoodsUnit(g.getGoodsUnit());
            items.setGoodsPrice(g.getPrice());
            items.setOrderId(order.getId());
            items.setDiscount(g.getDiscount());
            items.setGoodsNumber(g.getBuyNumber());
            order.itemsList.add(items);
            totalMoney += items.getGoodsPrice()*items.getGoodsNumber();
            actuallyMoney += items.getGoodsPrice()*items.getGoodsNumber()*items.getDiscount()/100;
        }
        order.setTotalMoney(totalMoney);
        order.setActualMoney(actuallyMoney);
        order.setStatus(OrderStatus.UNPID);
        outPut(order);
        outPut("确认支付吗？y/n");
        String ret = scanner.next();
        if ("YES".equalsIgnoreCase(ret) || "Y".equalsIgnoreCase(ret)) {
            order.setFinishTime(LocalDateTime.now());
            order.setStatus(OrderStatus.PAID);
            boolean effect = this.orderService.commitOrder(order);
            if(effect){
                for (Goods g :goodsList) {
                    boolean isUpdate = this.goodsService.updateAfterPay(g, g.getBuyNumber());
                    if (isUpdate) {
                        outPut("库存更新成功！");
                    }else{
                        outPut("库存更新失败！");
                    }
                }
            }else{
                outPut("订单没有支付成功，请重新下单！");
            }
        }
    }
}
