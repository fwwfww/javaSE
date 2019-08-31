package com.fww.utils.command.goods;

import com.fww.entity.Goods;
import com.fww.utils.annotation.AdminCommand;
import com.fww.utils.annotation.CommandMeta;
import com.fww.utils.command.AbstractCommand;
import com.fww.utils.command.Subject;

@AdminCommand
@CommandMeta(
        comm ="SJSP",
        name = "上架商品",
        group = "商品信息"
)
public class GoodsPutUpCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        outPut("上架商品");
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
        Goods goods = new Goods();
        goods.setGoodsName(goodsName);
        goods.setGoodsIntroduce(goodsIntroduce);
        goods.setGoodsUnit(goodsUnit);
        goods.setGoodsStock(goodsNum);
        goods.setPrice(price);
        goods.setDiscount(discount);
        boolean effect = this.goodsService.goodsPutUp(goods);
        if(effect){
            outPut("商品上架成功！");
        }else{
            outPut("商品上架失败！");
        }
    }
}
