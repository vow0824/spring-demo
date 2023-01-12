package com.vow.springframework.jdbc.core;

import java.util.List;
import java.util.Map;

/**
 * Interface specifying a basic set of JDBC operations.
 * @author: wushaopeng
 * @date: 2023/1/11 17:20
 */
public interface JdbcOperations {

    <T> T execute(StatementCallback<T> action) throws Exception;

    void execute(String sql);

    <T> T query(String sql, ResultSetExtractor<T> res);

    <T> List<T> query(String sql, RowMapper<T> rowMapper);

    List<Map<String, Object>> queryForList(String sql);
}
