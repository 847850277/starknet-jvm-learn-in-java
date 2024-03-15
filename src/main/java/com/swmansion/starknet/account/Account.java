package com.swmansion.starknet.account;

import com.swmansion.starknet.data.types.Felt;
import com.swmansion.starknet.data.types.Call;
import com.swmansion.starknet.data.types.StarknetChainId;
import com.swmansion.starknet.data.types.ExecutionParams;
import com.swmansion.starknet.data.InvokeParamsV3;
import com.swmansion.starknet.data.types.transactions.InvokeTransactionV1Payload;
import com.swmansion.starknet.data.types.transactions.InvokeTransactionV3Payload;

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
}
