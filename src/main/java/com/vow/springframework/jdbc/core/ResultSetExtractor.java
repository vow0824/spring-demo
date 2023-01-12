package com.vow.springframework.jdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Callback interface used by {@link JdbcTemplate}'s query methods.
 * @author: wushaopeng
 * @date: 2023/1/11 17:22
 */
public interface ResultSetExtractor<T> {

    T extractData(ResultSet rs) throws SQLException;
}
