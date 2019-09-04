package com.fww.utils.command.account;

import com.fww.entity.Account;
import com.fww.utils.annotation.CommandMeta;
import com.fww.utils.annotation.CustomerCommand;
import com.fww.utils.command.AbstractCommand;
import com.fww.utils.command.Subject;

@CustomerCommand
@CommandMeta(
        comm = "CKZH",
        name = "查看账户",
        group = "账户信息"
)
public class CustomerBrowse extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        outPut("账户信息");
        Account account = new Account();
        account = this.accountService.findAccountById(subject.getAccount().getId());
        if(account != null){
            outPut(account);
        }
    }
}
