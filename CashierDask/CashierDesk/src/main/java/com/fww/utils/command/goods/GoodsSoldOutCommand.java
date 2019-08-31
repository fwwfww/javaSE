package com.fww.utils.command.goods;

import com.fww.entity.Goods;
import com.fww.utils.annotation.AdminCommand;
import com.fww.utils.annotation.CommandMeta;
import com.fww.utils.command.AbstractCommand;
import com.fww.utils.command.Subject;

@AdminCommand
@CommandMeta(
        comm = "XJSP",
        name = "下架商品",
        group = "商品信息"
)
public class GoodsSoldOutCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
         outPut("请输入您所想要下架商品的id：");
         int id = scanner.nextInt();
        Goods goods = this.goodsService.findGoodsById(id);
        if(goods == null){
            outPut("您要下架的商品不存在！");
            return;
        }
        outPut(goods);
        outPut("确定要下架吗：y/n");
        String ret = scanner.next();
        if("YES".equalsIgnoreCase(ret)||"Y".equalsIgnoreCase(ret)){
            boolean effect = this.goodsService.soldOut(id);
            if(effect){
                outPut("商品下架成功！");
            }else{
                outPut("商品下架失败！");
            }
        }else{
            outPut("您选择了不下架！");
        }
    }
}
