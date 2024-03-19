package com.swmansion.starknet.data.types.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TransactionExecutionStatus {

    @JsonProperty("SUCCEEDED")
    SUCCEEDED,

    @JsonProperty("REVERTED")
    REVERTED,

    @JsonProperty("REJECTED")
    REJECTED,
}
