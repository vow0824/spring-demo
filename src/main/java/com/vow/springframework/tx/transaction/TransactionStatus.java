package com.vow.springframework.tx.transaction;

import java.io.Flushable;
import java.io.IOException;

/**
 * @author: wushaopeng
 * @date: 2023/1/13 9:31
 */
public interface TransactionStatus extends TransactionExecution, SavepointManager, Flushable {

    boolean hasSavepoint();

    @Override
    void flush() throws IOException;
}
