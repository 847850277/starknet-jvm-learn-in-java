package com.swmansion.starknet.data.types.transactions;

import com.swmansion.starknet.data.types.Felt;

import java.io.Serializable;
import java.util.List;

public abstract class Transaction implements Serializable {

    public abstract Felt getHash();
    public abstract TransactionVersion getVersion();
    public abstract List<Felt> getSignature();
    public abstract Felt getNonce();
    public abstract TransactionType getType();
}
