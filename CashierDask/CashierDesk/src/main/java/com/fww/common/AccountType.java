package com.fww.common;

import lombok.Getter;
import lombok.Setter;

@Getter

public enum  AccountType {
    ADMIN(1,"管理员"),CUSTOMER(2,"普通用户");
    public int flag;
    public String type;

    AccountType(int flag, String type) {
        this.flag = flag;
        this.type = type;
    }
    public static AccountType getAccountType(int flag)
    {
        for (AccountType accountType:values()) {
            if(accountType.flag == flag){
                return accountType;
            }
        }
        throw new RuntimeException("accountType"+flag+"not found!");
    }
}
