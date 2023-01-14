package com.vow.springframework.jdbc.datasource;

import cn.hutool.core.lang.Assert;

import java.sql.Connection;

/**
 * @author: wushaopeng
 * @date: 2023/1/13 9:02
 */
public class ConnectionHolder {

    private ConnectionHandle connectionHandle;

    private Connection currentConnection;

    public ConnectionHolder(ConnectionHandle connectionHandle) {
        this.connectionHandle = connectionHandle;
    }

    public ConnectionHolder(Connection connection) {
        this.connectionHandle = new SimpleConnectionHandle(connection);
    }

    public ConnectionHandle getConnectionHandle() {
        return connectionHandle;
    }

    protected boolean hasConnection() {
        return this.currentConnection != null;
    }

    protected void setConnection(Connection connection) {
        if (null != this.currentConnection) {
            if (null != this.connectionHandle) {
                this.connectionHandle.releaseConnection(this.currentConnection);
            }
            this.connectionHandle = null;
        }
        if (null != connection) {
            this.connectionHandle = new SimpleConnectionHandle(connection);
        } else {
            this.connectionHandle = null;
        }
    }

    protected Connection getConnection() {
        Assert.notNull(this.connectionHandle, "Active connection is required");
        if (null == this.currentConnection) {
            this.currentConnection = this.connectionHandle.getConnection();
        }
        return this.currentConnection;
    }
}
