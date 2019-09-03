package com.fww.utils.command.goods;

import com.fww.entity.Goods;
import com.fww.utils.annotation.AdminCommand;
import com.fww.utils.annotation.CommandMeta;
import com.fww.utils.command.AbstractCommand;
import com.fww.utils.command.Subject;

@AdminCommand
@CommandMeta(
        comm = "SPGX",
        name = "商品更新",
        group = "商品信息"
)
public class GoodsUpdateCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        outPut("商品更新");
        outPut("请输入您要更新商品的id:");
        int id = scanner.nextInt();
        Goods goods = this.goodsService.findGoodsById(id);
        if (goods == null) {
            outPut("您想要更新的商品不存在！");
            return;
        }
        outPut("商品信息如下：");
        outPut(goods);
        outPut("确认要更新吗？y/n");
        String ret = scanner.next();
        if ("YES".equalsIgnoreCase(ret) || "Y".equalsIgnoreCase(ret)) {
            outPut("请输入商品名称：");
            String goodsName = scanner.next();
            outPut("请输入商品简介：");
            String goodsIntroduce = scanner.next();
            outPut("请输入商品库存：");
            int goodsNum = scanner.nextInt();
            outPut("请输入商品单位：");
            String goodsUnit = scanner.next();
            outPut("请输入商品价格：");
            Double price = scanner.nextDouble();
            outPut("请输入商品折扣：75表示75折");
            int discount = scanner.nextInt();
            Goods goods1 = new Goods();
            goods1.setId(id);
            goods1.setGoodsName(goodsName);
            goods1.setGoodsIntroduce(goodsIntroduce);
            goods1.setGoodsUnit(goodsUnit);
            goods1.setGoodsStock(goodsNum);
            goods1.setPrice(price);
            goods1.setDiscount(discount);
            boolean effect = this.goodsService.goodsUpData(goods1);
            if(effect){
                outPut("更新成功！");
            }else{
                outPut("更新失败！");
            }
        }else{
            outPut("您选择了不更新！");
        }
    }
}
