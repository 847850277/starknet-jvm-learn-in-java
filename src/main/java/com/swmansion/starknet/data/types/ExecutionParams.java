package com.swmansion.starknet.data.types;

import com.swmansion.starknet.data.types.Felt;
import com.swmansion.starknet.data.types.ParamsBase;

public class ExecutionParams extends ParamsBase {
    private Felt nonce;
    private Felt maxFee;

    public ExecutionParams(Felt nonce, Felt maxFee) {
        this.nonce = nonce;
        this.maxFee = maxFee;
    }

    public Felt getNonce() {
        return nonce;
    }

    public Felt getMaxFee() {
        return maxFee;
    }
}
