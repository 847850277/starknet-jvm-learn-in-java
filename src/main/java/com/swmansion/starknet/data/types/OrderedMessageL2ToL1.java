package com.swmansion.starknet.data.types;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.swmansion.starknet.data.types.Felt;
import lombok.Data;

import java.util.List;

@Data
public class OrderedMessageL2ToL1 {

    @JsonProperty("order")
    private int order;

    @JsonProperty("from_address")
    private Felt fromAddress;

    @JsonProperty("to_address")
    private Felt toAddress;

    @JsonProperty("payload")
    private List<Felt> payload;
}
