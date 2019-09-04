package com.fww.service;
import com.fww.dao.AccountDao;
import com.fww.entity.Account;

import java.util.List;

public class AccountService {
    private AccountDao accountDao;
    public AccountService(){
        this.accountDao = new AccountDao();
    }
    public boolean register(Account account) {
      return accountDao.register(account);
    }

    public Account login(String username, String password) {
        return accountDao.login(username,password);
    }

    public Account findAccountById(Integer id) {
        return this.accountDao.findAccountById(id);
    }

    public List<Account> findAccount() {
        return this.accountDao.findAccount();
    }

    public boolean changePassword(Integer id, String ps1) {
        return this.accountDao.changePassword(id,ps1);
    }

    public boolean stopTheAccount(int id) {
        return this.accountDao.stopTheAccount(id);
    }
}
