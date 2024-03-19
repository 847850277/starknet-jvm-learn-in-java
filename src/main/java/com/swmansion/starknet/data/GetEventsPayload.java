package com.swmansion.starknet.data;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.swmansion.starknet.data.types.Felt;
import lombok.Data;

import java.util.List;

@Data
public class GetEventsPayload {

    @JsonProperty("from_block")
    private BlockId fromBlockId;

    @JsonProperty("to_block")
    private BlockId toBlockId;

    @JsonProperty("address")
    private Felt address;

    @JsonProperty("keys")
    private List<List<Felt>> keys;

    @JsonProperty("chunk_size")
    private int chunkSize;

    @JsonProperty("continuation_token")
    private String continuationToken;
}
