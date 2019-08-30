package com.fww.utils.command.account;

import com.fww.utils.annotation.AdminCommand;
import com.fww.utils.annotation.CommandMeta;
import com.fww.utils.command.AbstractCommand;
import com.fww.utils.command.Subject;

@AdminCommand
@CommandMeta(
        comm = "QTZH",
        name = "启停账户",
        group = "账户信息"
)
public class AccountStatusResetCommand extends AbstractCommand {

    @Override
    public void execute(Subject subject) {

    }
}
