package com.swmansion.starknet.data;

import com.swmansion.starknet.data.types.Felt;
import lombok.Data;

@Data
public class Hash extends BlockId {
    private Felt blockHash;

    public Hash(Felt blockHash) {
        this.blockHash = blockHash;
    }

    @Override
    public String toString() {
        return blockHash.hexString();
    }

    // getters and setters
}
