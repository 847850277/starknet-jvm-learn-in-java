package com.swmansion.starknet.data.types.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataResources {

    @JsonProperty("l1_gas")
    private int l1Gas;

    @JsonProperty("l1_data_gas")
    private int l1DataGas;

    // constructor, getters and setters
    public DataResources(int l1Gas, int l1DataGas) {
        this.l1Gas = l1Gas;
        this.l1DataGas = l1DataGas;
    }

    public int getL1Gas() {
        return l1Gas;
    }

    public void setL1Gas(int l1Gas) {
        this.l1Gas = l1Gas;
    }

    public int getL1DataGas() {
        return l1DataGas;
    }

    public void setL1DataGas(int l1DataGas) {
        this.l1DataGas = l1DataGas;
    }
}
