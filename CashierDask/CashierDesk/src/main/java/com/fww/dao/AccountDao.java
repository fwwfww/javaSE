package com.fww.dao;

import com.fww.common.AccountStatus;
import com.fww.common.AccountType;
import com.fww.entity.Account;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AccountDao extends BaseDao {

    public boolean register(Account account) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;
        try {
            conn = this.getConnection(true);
            String sql = "insert into account(username,password,name,account_type) values(?,?,?,?)";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, account.getName());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getRealName());
            ps.setInt(4, account.getType().getFlag());
            int ret = ps.executeUpdate();

            if (ret > 0) {
                flag = true;
            }
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                Integer id = rs.getInt(1);
                account.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResource(conn, ps, rs);
        }
        return flag;
    }

    public Account login(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;
        Account account = null;
        try {
            conn = this.getConnection(true);
            String sql = "select * from account where username=? and password=?";
            ps = conn.prepareStatement(sql);
            account = new Account();
            ps.setString(1, username);
            ps.setString(2, DigestUtils.md5Hex(password));
            rs = ps.executeQuery();
            while (rs.next()) {
              account = this.getAccount(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResource(conn, ps, rs);
        }
        return account;
    }

    public Account findAccountById(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Account account = null;
        try {
            conn = this.getConnection(true);
            String sql = "select * from account where id=?";
            ps = conn.prepareStatement(sql);
            account = new Account();
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
             account = this.getAccount(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResource(conn, ps, rs);
        }
        return account;
    }

    public List<Account> findAccount() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Account> accounts = new ArrayList<>();
        try {
            conn = this.getConnection(true);
            String sql = "select * from account";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Account account = getAccount(rs);
                accounts.add(account);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResource(conn, ps, rs);
        }
        return accounts;
    }

    public Account getAccount(ResultSet rs) {
        Account account = new Account();
        try {
            account.setId(rs.getInt("id"));
            account.setName(rs.getString("username"));
            account.setRealName(rs.getString("name"));
            account.setPassword(rs.getString("password"));
            account.setType(AccountType.getAccountType(rs.getInt("account_type")));
            account.setStatus(AccountStatus.getAccountStatus(rs.getInt("account_status")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public boolean changePassword(Integer id, String ps1) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;
        try {
            conn = this.getConnection(true);
            String sql = "update account set password=? where id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, DigestUtils.md5Hex(ps1));
            ps.setInt(2,id);
            int ret = ps.executeUpdate();
            if(ret > 0){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResource(conn, ps, rs);
        }
        return flag;
    }

    public boolean stopTheAccount(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;
        try {
            conn = this.getConnection(true);
            String sql = "update account set account_status = ? where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,AccountStatus.LOCK.getFlag() );
            ps.setInt(2,id);
            int ret = ps.executeUpdate();
            if(ret > 0){
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResource(conn, ps, rs);
        }
        return flag;
    }
}
