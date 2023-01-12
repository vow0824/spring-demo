package com.vow.springframework.jdbc.core;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * An interface used by {@link JdbcTemplate} for mapping rows of a
 * @link java.sql.ResultSet} on a per-row basis.
 * @author: wushaopeng
 * @date: 2023/1/11 17:23
 */
public interface RowMapper<T> {

    T mapRow(ResultSet rs, int rowNum) throws SQLException;
}
