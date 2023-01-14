package com.vow.springframework.jdbc.datasource;

import cn.hutool.core.lang.Assert;
import com.vow.springframework.beans.factory.InitializingBean;
import com.vow.springframework.tx.transaction.CannotCreateTransactionException;
import com.vow.springframework.tx.transaction.TransactionDefinition;
import com.vow.springframework.tx.transaction.TransactionException;
import com.vow.springframework.tx.transaction.support.AbstractPlatformTransactionManager;
import com.vow.springframework.tx.transaction.support.DefaultTransactionStatus;
import com.vow.springframework.tx.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author: wushaopeng
 * @date: 2023/1/13 9:44
 */
public class DataSourceTransactionManager extends AbstractPlatformTransactionManager implements InitializingBean {

    private DataSource dataSource;

    public DataSourceTransactionManager() {
    }

    public DataSourceTransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
        afterPropertiesSet();
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected DataSource obtainDataSource() {
        DataSource dataSource = getDataSource();
        Assert.notNull(dataSource, "No DataSource set");
        return dataSource;
    }

    @Override
    protected Object doGetTransaction() throws TransactionException {
        DataSourceTransactionObject txObject = new DataSourceTransactionObject();
        ConnectionHolder conHolder = (ConnectionHolder) TransactionSynchronizationManager.getResource(obtainDataSource());
        txObject.setConnectionHolder(conHolder, false);
        return txObject;
    }

    @Override
    protected void doCommit(DefaultTransactionStatus status) throws TransactionException {
        DataSourceTransactionObject txObject = (DataSourceTransactionObject) status.getTransaction();
        Connection conn = txObject.getConnectionHolder().getConnection();
        try {
            conn.commit();
        } catch (SQLException e) {
            throw new TransactionException("Could not commit JDBC transaction", e);
        }
    }

    @Override
    protected void doRollback(DefaultTransactionStatus status) throws TransactionException {
        DataSourceTransactionObject txObject = (DataSourceTransactionObject) status.getTransaction();
        Connection conn = txObject.getConnectionHolder().getConnection();
        try {
            conn.rollback();
        } catch (SQLException e) {
            throw new TransactionException("Could not rollback JDBC transaction", e);
        }
    }

    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) throws TransactionException {
        DataSourceTransactionObject txObject = (DataSourceTransactionObject) transaction;
        Connection conn = null;
        try {
            Connection newConn = obtainDataSource().getConnection();
            txObject.setConnectionHolder(new ConnectionHolder(newConn), true);

            conn = txObject.getConnectionHolder().getConnection();
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }
            prepareTransactionalConnection(conn, definition);

            TransactionSynchronizationManager.bindResource(obtainDataSource(), txObject.getConnectionHolder());

        } catch (SQLException e) {
            try {
                assert conn != null;
                conn.close();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            txObject.setConnectionHolder(null, false);
            throw new CannotCreateTransactionException("Could not open Connection for Transaction", e);
        }
    }

    @Override
    public void afterPropertiesSet() {
        if (null == getDataSource()) {
            throw new IllegalArgumentException("Property 'datasource' is required");
        }
    }

    protected void prepareTransactionalConnection(Connection conn, TransactionDefinition definition) throws SQLException {
        if (definition.isReadOnly()) {
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("set transaction read only");
            }
        }
    }

    public static class DataSourceTransactionObject extends JdbcTransactionObjectSupport {

        private boolean newConnectionHolder;

        private boolean mustRestoreAutoCommit;

        public void setConnectionHolder(ConnectionHolder connectionHolder, boolean newConnectionHolder) {
            super.setConnectionHolder(connectionHolder);
            this.newConnectionHolder = newConnectionHolder;
        }
    }
}
