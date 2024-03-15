package com.swmansion.starknet.data;

import com.swmansion.starknet.crypto.StarknetCurve;
import com.swmansion.starknet.data.types.Felt;
import com.swmansion.starknet.data.types.StarknetChainId;
import com.swmansion.starknet.data.types.transactions.TransactionType;
import com.swmansion.starknet.data.types.transactions.TransactionVersion;

import java.util.List;

public class TransactionHashCalculator {

    private static final Felt L1_GAS_PREFIX = Felt.fromShortString("L1_GAS");
    private static final Felt L2_GAS_PREFIX = Felt.fromShortString("L2_GAS");

    public static Felt calculateInvokeTxV1Hash(
            Felt contractAddress,
            List<Felt> calldata,
            StarknetChainId chainId,
            TransactionVersion version,
            Felt nonce,
            Felt maxFee
    ) {
        return transactionHashCommon(
                TransactionType.INVOKE,
                version,
                contractAddress,
                Felt.ZERO,
                calldata,
                maxFee,
                chainId,
                nonce
        );
    }

    private static Felt transactionHashCommon(
            TransactionType txType,
            TransactionVersion version,
            Felt contractAddress,
            Felt entryPointSelector,
            List<Felt> calldata,
            Felt maxFee,
            StarknetChainId chainId,
            Felt nonce
    ) {
        return StarknetCurve.pedersenOnElements(
                txType.getTxPrefix(),
                version.getValue(),
                contractAddress,
                entryPointSelector,
                StarknetCurve.pedersenOnElements(calldata),
                maxFee,
                chainId.getValue(),
                nonce
        );
    }



}
