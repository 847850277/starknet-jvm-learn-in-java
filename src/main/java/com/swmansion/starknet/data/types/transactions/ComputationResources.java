package com.swmansion.starknet.data.types.transactions;

import lombok.Data;

@Data
public class ComputationResources extends Resources {

    // constructor, getters and setters
    public ComputationResources(int steps, Integer memoryHoles, Integer rangeCheckApplications, Integer pedersenApplications, Integer poseidonApplications, Integer ecOpApplications, Integer ecdsaApplications, Integer bitwiseApplications, Integer keccakApplications, Integer segmentArenaApplications) {
        super(steps, memoryHoles, rangeCheckApplications, pedersenApplications, poseidonApplications, ecOpApplications, ecdsaApplications, bitwiseApplications, keccakApplications, segmentArenaApplications);
    }
}
