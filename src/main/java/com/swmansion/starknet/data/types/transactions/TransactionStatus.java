package com.swmansion.starknet.data.types.transactions;

import com.fasterxml.jackson.annotation.JsonAlias;

public enum TransactionStatus {

    @JsonAlias({"PENDING", "RECEIVED"})
    PENDING,

    @JsonAlias({"ACCEPTED_ON_L1"})
    ACCEPTED_ON_L1,

    @JsonAlias({"ACCEPTED_ON_L2"})
    ACCEPTED_ON_L2,

    @JsonAlias({"REJECTED"})
    REJECTED,

    @JsonAlias({"UNKNOWN", "NOT_RECEIVED"})
    UNKNOWN,

    @JsonAlias({"REVERTED"})
    REVERTED,
}
