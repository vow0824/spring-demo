package com.vow.springframework.jdbc.core;

import cn.hutool.core.lang.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: wushaopeng
 * @date: 2023/1/12 9:20
 */
public class RowMapperResultSetExtractor<T> implements ResultSetExtractor<List<T>> {

    private final RowMapper<T> rowMapper;

    private final int rowsExpected;

    /**
     * Create a new RowMapperResultSetExtractor.
     * @param rowMapper the RowMapper which creates an object for each row
     */
    public RowMapperResultSetExtractor(RowMapper<T> rowMapper) {
        this(rowMapper, 0);
    }

    public RowMapperResultSetExtractor(RowMapper<T> rowMapper, int rowsExpected) {
        Assert.notNull(rowMapper, "RowMapper is Required");
        this.rowMapper = rowMapper;
        this.rowsExpected = rowsExpected;
    }

    @Override
    public List<T> extractData(ResultSet rs) throws SQLException {
        List<T> results = rowsExpected > 0 ? new ArrayList<>(rowsExpected) : new ArrayList<>();
        int rowNum = 0;
        while (rs.next()) {
            results.add(rowMapper.mapRow(rs, rowNum++));
        }
        return results;
    }
}
