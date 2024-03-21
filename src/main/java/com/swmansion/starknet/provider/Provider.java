package com.swmansion.starknet.provider;

import com.swmansion.starknet.data.BlockTag;
import com.swmansion.starknet.data.types.Felt;

public interface Provider {

    /**
     * Get a nonce.
     *
     * Get a nonce of an account contract of a given address for pending block.
     *
     * @param contractAddress address of account contract
     *
     * @throws RequestFailedException
     */
    public Request<Felt> getNonce(Felt contractAddress);

    /**
     * Get a nonce.
     *
     * Get a nonce of an account contract of a given address for specified block tag.
     *
     * @param contractAddress address of account contract
     * @param blockTag block tag used for returning this value
     *
     * @throws RequestFailedException
     */
    public Request<Felt> getNonce(Felt contractAddress, BlockTag blockTag);

    /**
     * Get a nonce.
     *
     * Get a nonce of an account contract of a given address for specified block number.
     *
     * @param contractAddress address of account contract
     * @param blockNumber block number used for returning this value
     *
     * @throws RequestFailedException
     */
    public Request<Felt> getNonce(Felt contractAddress, int blockNumber);

    /**
     * Get a nonce.
     *
     * Get a nonce of an account contract of a given address for specified block hash.
     *
     * @param contractAddress address of account contract
     * @param blockHash block hash used for returning this value
     *
     * @throws RequestFailedException
     */
    public Request<Felt> getNonce(Felt contractAddress, Felt blockHash);
}
