package com.swmansion.starknet.data.types;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.swmansion.starknet.data.types.Felt;
import lombok.Data;

import java.util.List;

@Data
public class MessageL2ToL1 {

    @JsonAlias({"from_address"})
    @JsonProperty("from_address")
    private Felt fromAddress;

    @JsonAlias({"to_address"})
    @JsonProperty("to_address")
    private Felt toAddress;

    @JsonAlias({"payload"})
    @JsonProperty("payload")
    private List<Felt> payload;
}
