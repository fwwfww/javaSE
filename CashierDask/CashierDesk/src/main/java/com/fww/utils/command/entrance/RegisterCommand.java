package com.fww.utils.command.entrance;

import com.fww.common.AccountType;
import com.fww.entity.Account;
import com.fww.utils.annotation.CommandMeta;
import com.fww.utils.annotation.EntranceCommand;
import com.fww.utils.command.AbstractCommand;
import com.fww.utils.command.Subject;
import org.apache.commons.codec.digest.DigestUtils;


@EntranceCommand
@CommandMeta(
        comm = "ZC",
        name = "注册",
        group = "入口命令"
        )
public class RegisterCommand extends AbstractCommand {
        @Override
        public void execute(Subject subject) {
                outPut("请输入您的名称：");
                String name = scanner.next();
                outPut("请输入您的真实姓名：");
                String realName = scanner.next();
                outPut("请输入您的密码：");
                String password = scanner.next();
                outPut("请再次输入您的密码：");
                String password2 = scanner.next();
                if(password.equals(password2)) {
                        outPut("请输入您的账户类型:(1.管理员  2.用户)");
                        int accountType = scanner.nextInt();
                        AccountType accountType1 = AccountType.getAccountType(accountType);
                        Account account = new Account();
                        account.setName(name);
                        account.setRealName(realName);
                        account.setPassword(DigestUtils.md5Hex(password));
                        account.setType(accountType1);

                        boolean effect = accountService.register(account);
                        if(effect){
                                outPut("注册成功！");
                        }else{
                                outPut("注册失败！");
                        }
                }else{
                        outPut("您两次输入密码不一致，注册失败！");
                        return;
                }
        }
}
