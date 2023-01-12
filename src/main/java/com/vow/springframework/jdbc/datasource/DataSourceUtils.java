package com.vow.springframework.jdbc.datasource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Helper class that provides static methods for obtaining JDBC Connections from a {@link javax.sql.DataSource}.
 * @author: wushaopeng
 * @date: 2023/1/11 17:18
 */
public abstract class DataSourceUtils {

    /**
     * Obtain a Connection from the given DataSource.
     */
    public static Connection getConnection(DataSource dataSource) {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to obtain JDBC Conection", e);
        }
    }
}
