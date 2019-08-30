package com.fww.utils.command.common;

import com.fww.utils.annotation.AdminCommand;
import com.fww.utils.annotation.CommandMeta;
import com.fww.utils.annotation.CustomerCommand;
import com.fww.utils.annotation.EntranceCommand;
import com.fww.utils.command.AbstractCommand;
import com.fww.utils.command.Subject;
@EntranceCommand
@AdminCommand
@CustomerCommand
@CommandMeta(
        comm = "GYXT",
        name = "关于系统",
        group = "公共命令"
)
public class AboutSystemCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        outPut("************收银台系统*************");
        outPut("************作者：fww*************");
        outPut("************版本：1.0*************");
    }
}
