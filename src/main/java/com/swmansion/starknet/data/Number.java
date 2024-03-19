package com.swmansion.starknet.data;

import lombok.Data;

@Data
public class Number extends BlockId {
    private int blockNumber;

    public Number(int blockNumber) {
        this.blockNumber = blockNumber;
    }

    // getters and setters
}
