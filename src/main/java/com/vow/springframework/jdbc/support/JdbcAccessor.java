package com.vow.springframework.jdbc.support;

import cn.hutool.core.lang.Assert;
import com.vow.springframework.beans.factory.InitializingBean;

import javax.sql.DataSource;

/**
 * Base class for {@link com.vow.springframework.jdbc.core.JdbcTemplate} and
 * other JDBC-accessing DAO helpers, defining common properties such as
 * DataSource and exception translator.
 * @author: wushaopeng
 * @date: 2023/1/11 17:11
 */
public abstract class JdbcAccessor implements InitializingBean {

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Obtain the DataSource for actual use.
     * @return the DataSource (never {@code null})
     * @throws IllegalStateException in case of no DataSource set
     * @since 5.0
     */
    protected DataSource obtainDataSource() {
        DataSource dataSource = getDataSource();
        Assert.state(dataSource != null, "No DataSource Set");
        return dataSource;
    }

    /**
     * Eagerly initialize the exception translator, if demanded,
     * creating a default one for the specified DataSource if none set.
     */
    @Override
    public void afterPropertiesSet() {
        if (getDataSource() == null) {
            throw new IllegalArgumentException("Property 'dataSource' is required");
        }
    }
}
