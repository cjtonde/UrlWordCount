package com.rakuten.src.dao.data.impl;

import com.rakuten.src.dao.data.model.RdbDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by chetantonde on 10/23/16.
 */
public class RdbDaoImpl implements RdbDao {
    
    private static String userName;
    private static String password;
    private static String jdbcUrl;
    
    public RdbDaoImpl(String _username, String _password, String _url) {
        userName = _username;
        password = _password;
        jdbcUrl = _url;
    }
    
    public Connection getRdbConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(jdbcUrl, userName, password);
        return conn;
    }
    
    
}
