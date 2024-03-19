package com.swmansion.starknet.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import com.swmansion.starknet.data.types.Felt;

import java.util.List;

@Data
public class OrderedEvent {

    @JsonProperty("order")
    private int order;

    @JsonProperty("keys")
    private List<Felt> keys;

    @JsonProperty("data")
    private List<Felt> data;
}
