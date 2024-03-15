package com.swmansion.starknet.account;

import com.swmansion.starknet.data.InvokeParamsV3;
import com.swmansion.starknet.data.types.*;
import com.swmansion.starknet.data.types.transactions.InvokeTransactionV1Payload;
import com.swmansion.starknet.data.types.transactions.InvokeTransactionV3Payload;
import com.swmansion.starknet.provider.Provider;
import com.swmansion.starknet.signer.Signer;

import java.util.List;

public class StandardAccount implements Account {

    private Felt address;
    private Signer signer;
    private Provider provider;
    private StarknetChainId chainId;
    private Felt cairoVersion;

    public StandardAccount(Felt address, Signer signer, Provider provider, StarknetChainId chainId, Felt cairoVersion) {
        this.address = address;
        this.signer = signer;
        this.provider = provider;
        this.chainId = chainId;
        this.cairoVersion = cairoVersion;
    }

    public StandardAccount(Felt address, Felt privateKey, Provider provider, StarknetChainId chainId, Felt cairoVersion) {
        this(address, new StarkCurveSigner(privateKey), provider, chainId, cairoVersion);
    }

    @Override
    public Felt getAddress() {
        return address;
    }

    @Override
    public StarknetChainId getChainId() {
        return chainId;
    }

    @Override
    public InvokeTransactionV1Payload signV1(Call call, ExecutionParams params, boolean forFeeEstimate) {
        return null;
    }

    @Override
    public InvokeTransactionV3Payload signV3(Call call, InvokeParamsV3 params, boolean forFeeEstimate) {
        return null;
    }

    @Override
    public InvokeTransactionV1Payload signV1(Call call, ExecutionParams params) {
        return null;
    }

    @Override
    public InvokeTransactionV3Payload signV3(Call call, InvokeParamsV3 params) {
        return null;
    }

    @Override
    public InvokeTransactionV1Payload signV1(List<Call> calls, ExecutionParams params, boolean forFeeEstimate) {
        return null;
    }

    @Override
    public InvokeTransactionV3Payload signV3(List<Call> calls, InvokeParamsV3 params, boolean forFeeEstimate) {
        return null;
    }

    @Override
    public InvokeTransactionV1Payload signV1(List<Call> calls, ExecutionParams params) {
        return null;
    }

    @Override
    public InvokeTransactionV3Payload signV3(List<Call> calls, InvokeParamsV3 params) {
        return null;
    }

    // Implement the remaining methods as per your requirements
}
