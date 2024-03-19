package com.swmansion.starknet.data;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.List;

@Data
public class GetEventsResult {

    @JsonAlias({"events"})
    private List<EmittedEvent> events;

    @JsonAlias({"continuation_token"})
    private String continuationToken;
}
