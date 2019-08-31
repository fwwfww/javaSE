package com.fww.utils.command.goods;

import com.fww.entity.Goods;
import com.fww.utils.annotation.AdminCommand;
import com.fww.utils.annotation.CommandMeta;
import com.fww.utils.annotation.CustomerCommand;
import com.fww.utils.command.AbstractCommand;
import com.fww.utils.command.Subject;

import java.util.ArrayList;
import java.util.List;

@AdminCommand
@CustomerCommand
@CommandMeta(
        comm = "LLSP",
        name = "浏览商品",
        group = "商品信息"
)
public class GoodsBrowseCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        List<Goods> goodsList = new ArrayList<>();
        goodsList = this.goodsService.goodsBrowse();
        if(goodsList != null){
            for (Goods goods: goodsList) {
                outPut(goods);
            }
        }
    }
}
