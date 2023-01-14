package com.vow.springframework.jdbc.datasource;

import java.sql.Connection;

/**
 * Simple interface to be implemented by handles for a JDBC Connection.
 * @author: wushaopeng
 * @date: 2023/1/12 17:28
 */
public interface ConnectionHandle {

    Connection getConnection();

    default void releaseConnection(Connection conn){}
}
