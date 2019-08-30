package com.fww.utils.command.goods;

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

    }
}
