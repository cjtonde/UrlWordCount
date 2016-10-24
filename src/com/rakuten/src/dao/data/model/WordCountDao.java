package com.rakuten.src.dao.data.model;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by chetantonde on 10/22/16.
 */
public interface WordCountDao {
    /**
     * Insert word and count HashMap into word count table.
     *
     * @param urlId      URL id.
     * @param countWords HashMap containing word and count.
     * @throws SQLException
     */
    void createWordCount(RdbDao dao, int urlId, Map<String, Integer> countWords) throws SQLException;
}
