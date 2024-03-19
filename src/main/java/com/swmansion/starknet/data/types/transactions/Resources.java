package com.swmansion.starknet.data.types.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Resources {

    @JsonProperty("steps")
    private int steps;

    @JsonProperty("memoryHoles")
    private Integer memoryHoles;

    @JsonProperty("rangeCheckApplications")
    private Integer rangeCheckApplications;

    @JsonProperty("pedersenApplications")
    private Integer pedersenApplications;

    @JsonProperty("poseidonApplications")
    private Integer poseidonApplications;

    @JsonProperty("ecOpApplications")
    private Integer ecOpApplications;

    @JsonProperty("ecdsaApplications")
    private Integer ecdsaApplications;

    @JsonProperty("bitwiseApplications")
    private Integer bitwiseApplications;

    @JsonProperty("keccakApplications")
    private Integer keccakApplications;

    @JsonProperty("segmentArenaApplications")
    private Integer segmentArenaApplications;

    // getters and setters
}