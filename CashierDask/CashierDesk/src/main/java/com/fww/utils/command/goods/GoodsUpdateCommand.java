package com.fww.utils.command.goods;

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

    }
}
