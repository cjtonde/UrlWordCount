package com.rakuten.src.dao.data.model;

import java.sql.SQLException;

/**
 * Created by chetantonde on 10/22/16.
 */

public interface RunsDao {
    /**
     * Insert a row into the runs table with url, urlid and timestamp.
     *
     * @param url Url to scrape for.
     * @return Return urlid created for the run.
     * @throws SQLException
     */
    int createRun(String url) throws SQLException;
}
