package com.swmansion.starknet.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.swmansion.starknet.data.types.Felt;
import lombok.Data;

import java.util.List;

@Data
public class EmittedEvent {

    @JsonProperty("from_address")
    private Felt address;

    @JsonProperty("keys")
    private List<Felt> keys;

    @JsonProperty("data")
    private List<Felt> data;

    @JsonProperty("block_hash")
    private Felt blockHash;

    @JsonProperty("block_number")
    private Integer blockNumber;

    @JsonProperty("transaction_hash")
    private Felt transactionHash;
}
