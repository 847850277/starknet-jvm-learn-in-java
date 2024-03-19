package com.swmansion.starknet.data.types;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum PriceUnit {

    @JsonProperty("WEI")
    WEI,

    @JsonProperty("FRI")
    @JsonAlias({"STRK"}) // TODO: (#344) RPC 0.5.0 legacy name, remove once Pathfinder is updated
    FRI,
}