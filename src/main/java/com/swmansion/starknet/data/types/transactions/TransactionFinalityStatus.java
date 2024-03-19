package com.swmansion.starknet.data.types.transactions;

import com.fasterxml.jackson.annotation.JsonAlias;

public enum TransactionFinalityStatus {
    @JsonAlias({"ACCEPTED_ON_L1"})
    ACCEPTED_ON_L1,

    @JsonAlias({"ACCEPTED_ON_L2"})
    ACCEPTED_ON_L2,

    @JsonAlias({"RECEIVED", "PENDING"})
    RECEIVED,

    @JsonAlias({"NOT_RECEIVED", "UNKNOWN"})
    NOT_RECEIVED,
}
