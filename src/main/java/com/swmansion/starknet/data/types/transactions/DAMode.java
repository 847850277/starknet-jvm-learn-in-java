package com.swmansion.starknet.data.types.transactions;

import com.fasterxml.jackson.annotation.JsonAlias;

public enum DAMode {
    @JsonAlias({"L1"})
    L1(0),

    @JsonAlias({"L2"})
    L2(1);

    private final int value;

    DAMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}