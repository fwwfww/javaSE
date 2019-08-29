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
    private AccountStatus status;
    private AccountType type;
}
