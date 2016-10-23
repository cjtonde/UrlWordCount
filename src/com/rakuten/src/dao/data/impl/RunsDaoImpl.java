package com.rakuten.src.dao.data.impl;

import com.rakuten.src.dao.data.model.RunsDao;

import java.sql.*;

/**
 * Created by chetantonde on 10/22/16.
 */
public class RunsDaoImpl implements RunsDao {
    
    private static String userName;
    private static String password;
    private static String jdbcUrl;
    private static final String DB_RUNS_TABLENAME = "runs";
    
    public RunsDaoImpl(String _username, String _password, String _url) {
        userName = _username;
        password = _password;
        jdbcUrl = _url;
    }
    
    public int createRun(String url) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs;
        int autoIncKeyFromApi = -1;
        
        try {
            con = DriverManager.getConnection(jdbcUrl, userName, password);
            stmt = con.createStatement();
            String query = "INSERT INTO " + DB_RUNS_TABLENAME + "(urlname) VALUES('" + url + "')";
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                autoIncKeyFromApi = rs.getInt(1);
            } else {
                throw new RuntimeException("Row not inserted!");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return autoIncKeyFromApi;
    }
}
