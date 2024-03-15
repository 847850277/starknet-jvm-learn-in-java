package com.swmansion.starknet.data.types.transactions;

import java.io.Serializable;

public abstract class TransactionPayload implements Serializable {
    public abstract TransactionType getType();
}
