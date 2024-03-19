package com.swmansion.starknet.data.types.transactions;

package com.swmansion.starknet.data.types.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExecutionResources extends Resources {

    @JsonProperty("data_availability")
    private DataResources dataAvailability;

    // constructor, getters and setters
    public ExecutionResources(int steps, Integer memoryHoles, Integer rangeCheckApplications, Integer pedersenApplications, Integer poseidonApplications, Integer ecOpApplications, Integer ecdsaApplications, Integer bitwiseApplications, Integer keccakApplications, Integer segmentArenaApplications, DataResources dataAvailability) {
        super(steps, memoryHoles, rangeCheckApplications, pedersenApplications, poseidonApplications, ecOpApplications, ecdsaApplications, bitwiseApplications, keccakApplications, segmentArenaApplications);
        this.dataAvailability = dataAvailability;
    }

    public DataResources getDataAvailability() {
        return dataAvailability;
    }

    public void setDataAvailability(DataResources dataAvailability) {
        this.dataAvailability = dataAvailability;
    }
}