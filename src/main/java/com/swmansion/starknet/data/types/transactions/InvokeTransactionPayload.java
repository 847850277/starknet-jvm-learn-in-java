package com.swmansion.starknet.data.types.transactions;

import java.io.Serializable;

public abstract class InvokeTransactionPayload extends TransactionPayload implements Serializable {

    private TransactionType type = TransactionType.INVOKE;

    @Override
    public TransactionType getType() {
        return type;
    }
}
