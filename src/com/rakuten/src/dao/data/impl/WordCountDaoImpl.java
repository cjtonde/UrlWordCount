package com.rakuten.src.dao.data.impl;

import com.rakuten.src.dao.data.model.WordCountDao;

import java.sql.*;
import java.util.Map;

/**
 * Created by chetantonde on 10/22/16.
 */

public class WordCountDaoImpl implements WordCountDao {
    private static String userName;
    private static String password;
    private static String jdbcUrl;
    
    private static final String DB_WORD_COUNT_TABLE_NAME = "wordcount";
    
    public WordCountDaoImpl(String _username, String _password, String _url) {
        userName = _username;
        password = _password;
        jdbcUrl = _url;
    }
    
    public void createWordCount(int urlId, Map<String, Integer> countWords) throws SQLException {
        Connection con = null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection(jdbcUrl, userName, password);
            stmt = con.createStatement();
            
            for (Map.Entry<String, Integer> entry : countWords.entrySet()) {
                String word = entry.getKey();
                Integer count = entry.getValue();
                String query;
                query = String.format("INSERT INTO %s(urlid, word, count) VALUES(%d, '%s', %d);",
                        DB_WORD_COUNT_TABLE_NAME, urlId, word, count);
                stmt.executeUpdate(query);
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}