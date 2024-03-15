package com.swmansion.starknet.data.types;

import java.util.List;
import com.swmansion.starknet.data.types.transactions.DAMode;

public abstract class ParamsV3 extends ParamsBase {
    private Felt nonce;
    private ResourceBoundsMapping resourceBounds;
    private Uint64 tip;
    private List<Felt> paymasterData;
    private DAMode nonceDataAvailabilityMode;
    private DAMode feeDataAvailabilityMode;

    public Felt getNonce() {
        return nonce;
    }

    public ResourceBoundsMapping getResourceBounds() {
        return resourceBounds;
    }

    public Uint64 getTip() {
        return tip;
    }

    public List<Felt> getPaymasterData() {
        return paymasterData;
    }

    public DAMode getNonceDataAvailabilityMode() {
        return nonceDataAvailabilityMode;
    }

    public DAMode getFeeDataAvailabilityMode() {
        return feeDataAvailabilityMode;
    }
}
