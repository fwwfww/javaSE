package com.fww.utils.command.account;

import com.fww.common.AccountStatus;
import com.fww.entity.Account;
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
     outPut("启停账户: ");
     outPut("请输入想要启停的id");
        int id = Integer.parseInt(scanner.next());
        Account account = this.accountService.findAccountById(id);
        outPut(account);
        outPut("确认启停该账户吗?y/n");
        String ret = scanner.next();
        if("YES".equalsIgnoreCase(ret) || "Y".equalsIgnoreCase(ret)){
            boolean effect = this.accountService.stopTheAccount(id);
            if(effect){
                account.setStatus(AccountStatus.LOCK);
                outPut("启停成功！");
            }else{
             outPut("启停失败！");
            }
        }else{
            outPut("启停失败！");
        }
    }
}
