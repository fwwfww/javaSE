package com.fww.common;

public enum  AccountType {
    ADMIN(1,"管理员"),CUSTOMER(2,"普通用户");
    private int flag;
    private String type;

    AccountType(int flag, String type) {
        this.flag = flag;
        this.type = type;
    }
    public AccountType getAccountType(int flag)
    {
        for (AccountType accountType:values()) {
            if(accountType.flag == flag){
                return accountType;
            }
        }
        throw new RuntimeException("accountType"+flag+"not found!");
    }
}
