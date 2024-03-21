package com.swmansion.starknet.account;

import com.swmansion.starknet.data.BlockTag;
import com.swmansion.starknet.data.types.Felt;
import com.swmansion.starknet.data.types.Call;
import com.swmansion.starknet.data.types.StarknetChainId;
import com.swmansion.starknet.data.types.ExecutionParams;
import com.swmansion.starknet.data.InvokeParamsV3;
import com.swmansion.starknet.data.types.transactions.InvokeTransactionV1Payload;
import com.swmansion.starknet.data.types.transactions.InvokeTransactionV3Payload;
import com.swmansion.starknet.provider.Request;

import java.util.List;

public interface Account {

    Felt getAddress();
    StarknetChainId getChainId();

    InvokeTransactionV1Payload signV1(Call call, ExecutionParams params, boolean forFeeEstimate);
    InvokeTransactionV3Payload signV3(Call call, InvokeParamsV3 params, boolean forFeeEstimate);

    InvokeTransactionV1Payload signV1(Call call, ExecutionParams params);
    InvokeTransactionV3Payload signV3(Call call, InvokeParamsV3 params);

    InvokeTransactionV1Payload signV1(List<Call> calls, ExecutionParams params, boolean forFeeEstimate);
    InvokeTransactionV3Payload signV3(List<Call> calls, InvokeParamsV3 params, boolean forFeeEstimate);

    InvokeTransactionV1Payload signV1(List<Call> calls, ExecutionParams params);
    InvokeTransactionV3Payload signV3(List<Call> calls, InvokeParamsV3 params);


    /**
     * Get account nonce.
     *
     * Get account nonce for pending block.
     *
     * @return nonce as field value.
     */
    public Request<Felt> getNonce();

    /**
     * Get account nonce.
     *
     * Get account nonce for specified block tag.
     *
     * @param blockTag block tag used for returning this value.
     * @return nonce as field value.
     */
    public Request<Felt> getNonce(BlockTag blockTag);

    /**
     * Get account nonce.
     *
     * Get account nonce for specified block hash.
     *
     * @param blockHash block hash used for returning this value.
     * @return nonce as field value.
     */
    public Request<Felt> getNonce(Felt blockHash);

    /**
     * Get account nonce.
     *
     * Get account nonce for specified block number.
     *
     * @param blockNumber block number used for returning this value.
     * @return nonce as field value.
     */
    public Request<Felt> getNonce(int blockNumber);
}
