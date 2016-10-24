package com.rakuten.src.dao.data.model;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by chetantonde on 10/23/16.
 */
public interface RdbDao {
    Connection getRdbConnection() throws SQLException;
}
