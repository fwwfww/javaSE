package com.fww.utils.command.order;

import com.fww.utils.annotation.CommandMeta;
import com.fww.utils.annotation.CustomerCommand;
import com.fww.utils.command.AbstractCommand;
import com.fww.utils.command.Subject;

@CustomerCommand
@CommandMeta(
        comm = "LLDD",
        name = "浏览订单",
        group = "订单信息"

)
public class OrderBrowseCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {

    }
}
