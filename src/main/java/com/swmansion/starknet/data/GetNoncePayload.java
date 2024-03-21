package com.swmansion.starknet.data;

import com.swmansion.starknet.data.types.Felt;

public class GetNoncePayload extends PayloadWithBlockId {
    private Felt contractAddress;
    private BlockId blockId;

    public GetNoncePayload(Felt contractAddress, BlockId blockId) {
        this.contractAddress = contractAddress;
        this.blockId = blockId;
    }

    public Felt getContractAddress() {
        return contractAddress;
    }

    @Override
    public BlockId getBlockId() {
        return blockId;
    }
}
