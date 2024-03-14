package com.swmansion.starknet.data.types;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;

public class ResourceBounds {
    @JsonProperty("max_amount")
    @JsonAlias({"MAX_AMOUNT"})
    private final Uint64 maxAmount;

    @JsonProperty("max_price_per_unit")
    @JsonAlias({"MAX_PRICE_PER_UNIT"})
    private final Uint128 maxPricePerUnit;

    public static final ResourceBounds ZERO = new ResourceBounds(Uint64.ZERO, Uint128.ZERO);

    public ResourceBounds(Uint64 maxAmount, Uint128 maxPricePerUnit) {
        this.maxAmount = maxAmount;
        this.maxPricePerUnit = maxPricePerUnit;
    }

    public Uint64 getMaxAmount() {
        return maxAmount;
    }

    public Uint128 getMaxPricePerUnit() {
        return maxPricePerUnit;
    }

    public Felt toMaxFee() {
        BigInteger result = maxAmount.getValue().multiply(maxPricePerUnit.getValue());
        return new Felt(result);
    }
}
