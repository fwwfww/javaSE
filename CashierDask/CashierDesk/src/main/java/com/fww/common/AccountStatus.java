package com.fww.common;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum  AccountStatus{
    UNLOCK(1,"启用"),LOCK(2,"暂停使用");
    private int flag;
    private String status;

    AccountStatus(int flag, String status) {
        this.flag = flag;
        this.status = status;
    }
    public static AccountStatus getAccountStatus(int flag){
        for (AccountStatus accountStatus:values()) {
         if(accountStatus.flag == flag){
             return accountStatus;
         }
        }
        throw new RuntimeException("AccountStatus"+flag+"is not found!");
    }
}
