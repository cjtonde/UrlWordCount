package com.rakuten.src.dao.data.model;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by chetantonde on 10/22/16.
 */
public interface WordCountDao {
    /**
     * Insert word and count Hashmap into word count table.
     *
     * @param urlId      URL id.
     * @param countWords HasMap containing word and count.
     * @throws SQLException
     */
    void createWordCount(int urlId, Map<String, Integer> countWords) throws SQLException;
}
