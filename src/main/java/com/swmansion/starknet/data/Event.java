package com.swmansion.starknet.data;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.swmansion.starknet.data.types.Felt;
import lombok.Data;

import java.util.List;

@Data
public class Event {

    @JsonAlias({"address", "from_address"})
    private Felt address;

    @JsonAlias({"keys"})
    private List<Felt> keys;

    @JsonAlias({"data"})
    private List<Felt> data;
}
