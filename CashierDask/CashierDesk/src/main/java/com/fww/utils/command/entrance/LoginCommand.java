package com.fww.utils.command.entrance;

import com.fww.common.AccountStatus;
import com.fww.entity.Account;
import com.fww.utils.annotation.CommandMeta;
import com.fww.utils.annotation.EntranceCommand;
import com.fww.utils.command.AbstractCommand;
import com.fww.utils.command.Subject;
@EntranceCommand
@CommandMeta(
        comm = "DL",
        name = "登录",
        group = "入口命令"
)
public class LoginCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        Account account = subject.getAccount();
        if(account == null) {
            outPut("请输入您的名称：");
            String username = scanner.next();
            outPut("请输入密码：");
            String password = scanner.next();
            account = accountService.login(username,password);
            if((account != null)&& account.getStatus() == AccountStatus.UNLOCK ){
                outPut(account.getName()+" "+account.getType()+" 登录成功！");
                subject.setAccount(account);
            }else{
                outPut("用户名或密码错误！");
            }
        }else{
            outPut("您已经登录过了！");
        }

    }
}
