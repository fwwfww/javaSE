package com.fww.utils.command.account;

import com.fww.utils.annotation.AdminCommand;
import com.fww.utils.annotation.CommandMeta;
import com.fww.utils.annotation.CustomerCommand;
import com.fww.utils.command.AbstractCommand;
import com.fww.utils.command.Subject;
import org.apache.commons.codec.digest.DigestUtils;

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
        outPut("修改密码:");
        outPut("请输入密码:");
        String pas = scanner.next();
        if(!DigestUtils.md5Hex(pas).equals(subject.getAccount().getPassword())){
            outPut("密码输入错误！");
            return;
        }
        outPut("请输入新的密码:");
        String ps1 = scanner.next();
        outPut("请再次输入密码:");
        String ps2 = scanner.next();
        if(!ps1.equals(ps2)){
            outPut("两次密码不一致，修改失败！");
            return;
        }
        boolean effect = this.accountService.changePassword(subject.getAccount().getId(),ps1);
        if(effect){
            outPut("修改成功！");
        }else{
            outPut("修改失败！");
        }
    }
}
