package com.fww.utils.command.account;

import com.fww.utils.annotation.AdminCommand;
import com.fww.utils.annotation.CommandMeta;
import com.fww.utils.annotation.CustomerCommand;
import com.fww.utils.command.AbstractCommand;
import com.fww.utils.command.Subject;

@AdminCommand
@CustomerCommand
@CommandMeta(
        comm="XGMM",
        name = "修改密码",
        group = "账户信息"
)
public class AccountPasswordResetCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {

    }
}
