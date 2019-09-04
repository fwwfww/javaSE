package com.fww.entity;

import com.fww.common.AccountStatus;
import com.fww.common.AccountType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Account {
    private Integer id;
    private String name;
    private String realName;
    private String password;
    private AccountStatus status;
    private AccountType type;
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("|个人编号|:").append(this.getId()).append("\n")
                .append("|用户名称|:").append(this.getName()).append("\n")
                .append("|真实名称|:").append(this.getRealName()).append("\n")
                .append("|个人密码|:").append(this.getPassword()).append("\n")
                .append("|账户状态|:") .append( this.getStatus()).append("\n")
                .append("|账户类型|:").append(this.getType()).append("\n");
        sb.append("======================================================");
        return sb.toString();
    }
}
