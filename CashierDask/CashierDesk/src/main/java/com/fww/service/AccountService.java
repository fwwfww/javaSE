package com.fww.service;
import com.fww.dao.AccountDao;
import com.fww.entity.Account;
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
}
