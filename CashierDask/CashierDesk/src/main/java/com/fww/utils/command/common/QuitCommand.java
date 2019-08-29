package com.fww.utils.command.common;

import com.fww.utils.annotation.AdminCommand;
import com.fww.utils.annotation.CommandMeta;
import com.fww.utils.annotation.CustomerCommand;

@AdminCommand
@CustomerCommand
@CommandMeta(
        comm = "TC",
        name = "退出",
        group = "公共命令"
)
public class QuitCommand {
}
