package com.vow.springframework.jdbc.core;

import com.vow.springframework.jdbc.UncategorizedSQLException;
import com.vow.springframework.jdbc.datasource.DataSourceUtils;
import com.vow.springframework.jdbc.support.JdbcAccessor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * @author: wushaopeng
 * @date: 2023/1/11 17:29
 */
public class JdbcTemplate extends JdbcAccessor implements JdbcOperations {

    /**
     * If this variable is set to a non-negative value, it will be used for setting the
     * fetchSize property on statements used for query processing.
     */
    private int fetchSize = -1;

    /**
     * If this variable is set to a non-negative value, it will be used for setting the
     * maxRows property on statements used for query processing.
     */
    private int maxRows = -1;

    /**
     * If this variable is set to a non-negative value, it will be used for setting the
     * queryTimeout property on statements used for query processing.
     */
    private int queryTimeout = -1;

    public JdbcTemplate() {
    }

    public JdbcTemplate(DataSource dataSource) {
        setDataSource(dataSource);
        afterPropertiesSet();
    }

    public int getFetchSize() {
        return fetchSize;
    }

    public void setFetchSize(int fetchSize) {
        this.fetchSize = fetchSize;
    }

    public int getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
    }

    public int getQueryTimeout() {
        return queryTimeout;
    }

    public void setQueryTimeout(int queryTimeout) {
        this.queryTimeout = queryTimeout;
    }

    @Override
    public <T> T execute(StatementCallback<T> action) {
        Connection conn = DataSourceUtils.getConnection(obtainDataSource());
        try {
            Statement statement = conn.createStatement();
            applyStatementSettings(statement);
            return action.doInStatement(statement);
        } catch (SQLException e) {
            throw new UncategorizedSQLException("ConnectionCallback", getSql(action), e);
        }
    }

    private static String getSql(Object sqlProvider) {
        if (sqlProvider instanceof SqlProvider) {
            return ((SqlProvider) sqlProvider).getSql();
        } else {
            return null;
        }
    }

    @Override
    public void execute(String sql) {
        class ExecuteStatementCallback implements StatementCallback<Object>, SqlProvider {

            @Override
            public String getSql() {
                return sql;
            }

            @Override
            public Object doInStatement(Statement statement) throws SQLException {
                statement.execute(sql);
                return null;
            }
        }
        execute(new ExecuteStatementCallback());
    }

    @Override
    public <T> T query(String sql, ResultSetExtractor<T> res) {
        class QueryStatementCallback implements StatementCallback<T>, SqlProvider {

            @Override
            public String getSql() {
                return sql;
            }

            @Override
            public T doInStatement(Statement statement) throws SQLException {
                ResultSet rs = statement.executeQuery(sql);
                return res.extractData(rs);
            }
        }
        return execute(new QueryStatementCallback());
    }

    @Override
    public <T> List<T> query(String sql, RowMapper<T> rowMapper) {
        return query(sql, new RowMapperResultSetExtractor<>(rowMapper));
    }

    @Override
    public List<Map<String, Object>> queryForList(String sql) {
        return query(sql, new ColumnMapRowMapper());
    }

    protected void applyStatementSettings(Statement statement) throws SQLException {
        int fetchSize = getFetchSize();
        if (fetchSize != -1) {
            statement.setFetchSize(fetchSize);
        }

        int maxRows = getMaxRows();
        if (maxRows != -1) {
            statement.setMaxRows(maxRows);
        }
    }
}
