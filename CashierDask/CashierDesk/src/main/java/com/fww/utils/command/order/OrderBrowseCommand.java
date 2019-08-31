package com.fww.utils.command.order;

import com.fww.entity.Items;
import com.fww.utils.annotation.CommandMeta;
import com.fww.utils.annotation.CustomerCommand;
import com.fww.utils.command.AbstractCommand;
import com.fww.utils.command.Subject;

import java.util.ArrayList;
import java.util.List;

@CustomerCommand
@CommandMeta(
        comm = "LLDD",
        name = "浏览订单",
        group = "订单信息"

)
public class OrderBrowseCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
       outPut("我的订单");
        List<Items> itemsList = new ArrayList<>();
        itemsList = this.orderService.quaryAllItems();
        if(itemsList == null){
            outPut("您还没有购买记录！");
            return;
        }
        for (Items items:itemsList){


        }
    }
}
