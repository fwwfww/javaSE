package com.fww.dao;


import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDao {
    private static volatile DataSource dataSource;
    private DataSource getDataSource(){
        if(dataSource == null){
            synchronized (DataSource.class){
                if(dataSource == null){
                    dataSource = new MysqlDataSource();
                    String host = "127.0.0.1";
                    String port = "3306";
                    ((MysqlDataSource)dataSource).setUrl("jdbc:mysql://" + (host + ":" + port) + "/shouyintai"+"?useSSL=false");
                    ((MysqlDataSource)dataSource).setUser("root");
                    ((MysqlDataSource)dataSource).setPassword("123456");
                }
            }
        }
        return dataSource;
    }

    protected Connection getConnection(boolean autoCommit) throws SQLException {
      Connection connection = this.getDataSource().getConnection();
      connection.setAutoCommit(autoCommit);
      return connection;
    }

    protected void closeResource(Connection connection, Statement ps, ResultSet rs) {
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
