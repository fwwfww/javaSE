package com.fww.utils.command.account;

import com.fww.utils.annotation.AdminCommand;
import com.fww.utils.annotation.CommandMeta;
import com.fww.utils.annotation.CustomerCommand;

@AdminCommand
@CustomerCommand
@CommandMeta(
        comm = "CKZH",
        name = "查看账户",
        group = "账户信息"
)
public class AccountBrowseCommand {
}
