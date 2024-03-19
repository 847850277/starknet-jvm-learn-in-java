package com.swmansion.starknet.data.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeePayment {

    @JsonProperty("amount")
    private Felt amount;

    @JsonProperty("unit")
    private PriceUnit unit;

    // constructor, getters and setters
    public FeePayment(Felt amount, PriceUnit unit) {
        this.amount = amount;
        this.unit = unit;
    }

    public Felt getAmount() {
        return amount;
    }

    public void setAmount(Felt amount) {
        this.amount = amount;
    }

    public PriceUnit getUnit() {
        return unit;
    }

    public void setUnit(PriceUnit unit) {
        this.unit = unit;
    }
}
