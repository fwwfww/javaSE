package com.fww.utils.command.account;

import com.fww.entity.Account;
import com.fww.utils.annotation.AdminCommand;
import com.fww.utils.annotation.CommandMeta;
import com.fww.utils.annotation.CustomerCommand;
import com.fww.utils.command.AbstractCommand;
import com.fww.utils.command.Subject;

import java.util.ArrayList;
import java.util.List;

@AdminCommand
@CommandMeta(
        comm = "CKZH",
        name = "查看账户",
        group = "账户信息"
)
public class AccountBrowseCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        outPut("账户信息：");
        List<Account> accounts = new ArrayList<>();
        accounts = this.accountService.findAccount();
        for (Account a :accounts) {
            outPut(a);
        }
    }
}
