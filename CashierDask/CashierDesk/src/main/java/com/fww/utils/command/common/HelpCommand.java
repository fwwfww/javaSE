package com.fww.utils.command.common;

import com.fww.utils.annotation.AdminCommand;
import com.fww.utils.annotation.CommandMeta;
import com.fww.utils.annotation.CustomerCommand;

@CustomerCommand
@AdminCommand
@CommandMeta(
        comm = "BZ",
        name = "帮助",
        group = "公众命令"
)
public class HelpCommand {
}
