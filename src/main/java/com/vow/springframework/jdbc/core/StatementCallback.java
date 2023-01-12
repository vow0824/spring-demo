package com.vow.springframework.jdbc.core;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Generic callback interface for code that operates on a JDBC Statement.
 * @author: wushaopeng
 * @date: 2023/1/11 17:21
 */
public interface StatementCallback<T> {

    T doInStatement(Statement statement) throws SQLException;
}
