package com.swmansion.starknet.data;

import lombok.Data;

@Data
public class Tag extends BlockId {

    private BlockTag blockTag;

    public Tag(BlockTag blockTag) {
        this.blockTag = blockTag;
    }

    @Override
    public String toString() {
        return blockTag.getTag();
    }

    // getters and setters
}
