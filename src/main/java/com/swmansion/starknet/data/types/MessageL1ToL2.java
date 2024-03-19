package com.swmansion.starknet.data.types;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.swmansion.starknet.data.types.Felt;

import java.util.List;

public class MessageL1ToL2 {

    @JsonAlias({"from_address"})
    @JsonProperty("from_address")
    private Felt fromAddress;

    @JsonAlias({"to_address"})
    @JsonProperty("to_address")
    private Felt toAddress;

    @JsonAlias({"selector", "entry_point_selector"})
    @JsonProperty("entry_point_selector")
    private Felt selector;

    @JsonAlias({"nonce"})
    @JsonProperty("nonce")
    private Felt nonce;

    @JsonAlias({"payload"})
    @JsonProperty("payload")
    private List<Felt> payload;
}
