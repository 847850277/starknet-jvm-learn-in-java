package com.swmansion.starknet.data.types;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResourceBoundsMapping {
    @JsonProperty("l1_gas")
    @JsonAlias({"L1_GAS"})
    private final ResourceBounds l1Gas;

    @JsonProperty("l2_gas")
    @JsonAlias({"L2_GAS"})
    private final ResourceBounds l2Gas;

    private ResourceBoundsMapping(ResourceBounds l1Gas, ResourceBounds l2Gas) {
        this.l1Gas = l1Gas;
        this.l2Gas = l2Gas;
    }

    public ResourceBoundsMapping(ResourceBounds l1Gas) {
        this(l1Gas, ResourceBounds.ZERO);
    }

    public ResourceBounds getL1Gas() {
        return l1Gas;
    }

    public ResourceBounds getL2Gas() {
        return l2Gas;
    }
}
