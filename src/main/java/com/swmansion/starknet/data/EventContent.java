package com.swmansion.starknet.data;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.swmansion.starknet.data.types.Felt;
import lombok.Data;

import java.util.List;

@Data
public class EventContent {

    @JsonAlias({"keys"})
    private List<Felt> keys;

    @JsonAlias({"data"})
    private List<Felt> data;
}
