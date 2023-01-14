package com.vow.springframework.jdbc.datasource;

/**
 * @author: wushaopeng
 * @date: 2023/1/13 9:14
 */
public abstract class JdbcTransactionObjectSupport {

    private ConnectionHolder connectionHolder;

    public ConnectionHolder getConnectionHolder() {
        return connectionHolder;
    }

    public void setConnectionHolder(ConnectionHolder connectionHolder) {
        this.connectionHolder = connectionHolder;
    }

    public boolean hasConnectionHolder() {
        return this.connectionHolder != null;
    }
}
