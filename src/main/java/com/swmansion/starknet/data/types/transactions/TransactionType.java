package com.swmansion.starknet.data.types.transactions;

import com.swmansion.starknet.data.types.Felt;

public enum TransactionType {
    DECLARE(Felt.fromHex("0x6465636c617265")),
    DEPLOY(Felt.fromHex("0x6465706c6f79")),
    DEPLOY_ACCOUNT(Felt.fromHex("0x6465706c6f795f6163636f756e74")),
    INVOKE(Felt.fromHex("0x696e766f6b65")),
    L1_HANDLER(Felt.fromHex("0x6c315f68616e646c6572"));

    private final Felt txPrefix;

    TransactionType(Felt txPrefix) {
        this.txPrefix = txPrefix;
    }

    public Felt getTxPrefix() {
        return txPrefix;
    }
}
