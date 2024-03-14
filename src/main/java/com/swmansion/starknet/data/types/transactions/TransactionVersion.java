package com.swmansion.starknet.data.types.transactions;

import com.swmansion.starknet.data.types.Felt;

import java.math.BigInteger;

public enum TransactionVersion {
    V0(Felt.fromHex("0x0")),
    V1(Felt.fromHex("0x1")),
    V1_QUERY(Felt.fromHex("0x100000000000000000000000000000001")),
    V2(Felt.fromHex("0x2")),
    V2_QUERY(Felt.fromHex("0x100000000000000000000000000000002")),
    V3(Felt.fromHex("0x3")),
    V3_QUERY(Felt.fromHex("0x100000000000000000000000000000003"));

    private final Felt value;

    TransactionVersion(Felt value) {
        this.value = value;
    }

    public Felt getValue() {
        return value;
    }
}
