package com.swmansion.starknet.data;

import com.swmansion.starknet.crypto.Poseidon;
import com.swmansion.starknet.crypto.StarknetCurve;
import com.swmansion.starknet.data.types.Felt;
import com.swmansion.starknet.data.types.ResourceBoundsMapping;
import com.swmansion.starknet.data.types.StarknetChainId;
import com.swmansion.starknet.data.types.Uint64;
import com.swmansion.starknet.data.types.transactions.DAMode;
import com.swmansion.starknet.data.types.transactions.TransactionType;
import com.swmansion.starknet.data.types.transactions.TransactionVersion;
import com.swmansion.starknet.extensions.ToFeltKt;

import java.math.BigInteger;
import java.util.*;

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


    public static Felt calculateDeployAccountV1TxHash(
            Felt classHash,
            List<Felt> calldata,
            Felt salt,
            StarknetChainId chainId,
            TransactionVersion version,
            Felt maxFee,
            Felt nonce
    ) {
        Felt contractAddress = ContractAddressCalculator.calculateAddressFromHash(
                classHash,
                calldata,
                salt
        );

        List<Felt> calldataList = new ArrayList<>();
        calldataList.add(classHash);
        calldataList.add(salt);
        calldataList.addAll(calldata);

        return transactionHashCommon(
                TransactionType.DEPLOY_ACCOUNT,
                version,
                contractAddress,
                Felt.ZERO,
                calldataList,
                maxFee,
                chainId,
                nonce
        );
    }

    public static Felt calculateDeclareV1TxHash(
            Felt classHash,
            StarknetChainId chainId,
            Felt senderAddress,
            Felt maxFee,
            TransactionVersion version,
            Felt nonce
    ) {
        Felt hash = StarknetCurve.pedersenOnElements(Collections.singletonList(classHash));

        return StarknetCurve.pedersenOnElements(
                TransactionType.DECLARE.getTxPrefix(),
                version.getValue(),
                senderAddress,
                Felt.ZERO,
                hash,
                maxFee,
                chainId.getValue(),
                nonce
        );
    }

    public static Felt calculateDeclareV2TxHash(
            Felt classHash,
            StarknetChainId chainId,
            Felt senderAddress,
            Felt maxFee,
            TransactionVersion version,
            Felt nonce,
            Felt compiledClassHash
    ) {
        Felt calldataHash = StarknetCurve.pedersenOnElements(Collections.singletonList(classHash));

        return StarknetCurve.pedersenOnElements(
                TransactionType.DECLARE.getTxPrefix(),
                version.getValue(),
                senderAddress,
                Felt.ZERO,
                calldataHash,
                maxFee,
                chainId.getValue(),
                nonce,
                compiledClassHash
        );
    }

    public static Felt prepareDataAvailabilityModes(DAMode feeDataAvailabilityMode, DAMode nonceDataAvailabilityMode) {
        BigInteger shiftedNonceValue = BigInteger.valueOf(nonceDataAvailabilityMode.getValue()).shiftLeft(32);
        BigInteger result = shiftedNonceValue.add(BigInteger.valueOf(feeDataAvailabilityMode.getValue()));
        return ToFeltKt.getToFelt(result);
    }

    public static Felt calculateInvokeTxV3Hash(
            Felt senderAddress,
            List<Felt> calldata,
            StarknetChainId chainId,
            TransactionVersion version,
            Felt nonce,
            Uint64 tip,
            ResourceBoundsMapping resourceBounds,
            List<Felt> paymasterData,
            List<Felt> accountDeploymentData,
            DAMode feeDataAvailabilityMode,
            DAMode nonceDataAvailabilityMode
    ) {
        List<Felt> felts = prepareCommonTransanctionV3Fields(
                TransactionType.INVOKE,
                version,
                senderAddress,
                tip,
                resourceBounds,
                paymasterData,
                chainId,
                nonce,
                nonceDataAvailabilityMode,
                feeDataAvailabilityMode
        );

        //felts
        Felt[] allFields = new Felt[felts.size() + 2];
        System.arraycopy(felts.toArray(new Felt[0]), 0, allFields, 0, felts.size());
        allFields[felts.size()] = Poseidon.poseidonHash(accountDeploymentData);
        allFields[felts.size() + 1] = Poseidon.poseidonHash(calldata);
        return Poseidon.poseidonHash(allFields);


//        return Poseidon.poseidonHash(
//                felts,
//                Poseidon.poseidonHash(accountDeploymentData),
//                Poseidon.poseidonHash(calldata)
//        );
    }

    private static List<Felt> prepareCommonTransanctionV3Fields(
            TransactionType txType,
            TransactionVersion version,
            Felt address,
            Uint64 tip,
            ResourceBoundsMapping resourceBounds,
            List<Felt> paymasterData,
            StarknetChainId chainId,
            Felt nonce,
            DAMode nonceDataAvailabilityMode,
            DAMode feeDataAvailabilityMode
    ) {
        List<Felt> result = new ArrayList<>();
        result.add(txType.getTxPrefix());
        result.add(version.getValue());
        result.add(address);
        Map.Entry<Felt, Felt> resourceBoundsEntry = prepareResourceBoundsForFee(resourceBounds);
        Felt[] resourceBoundsArray = new Felt[]{ToFeltKt.getToFelt(tip),resourceBoundsEntry.getKey(), resourceBoundsEntry.getValue()};
        //List<Felt> list = Arrays.asList(, resourceBoundsEntry);
        Felt felt = Poseidon.poseidonHash(resourceBoundsArray);
        result.add(felt);
        result.add(Poseidon.poseidonHash(paymasterData));
        result.add(ToFeltKt.getToFelt(chainId.getValue()));
        result.add(nonce);
        result.add(prepareDataAvailabilityModes(
                feeDataAvailabilityMode,
                nonceDataAvailabilityMode
        ));

        return result;
    }

    private  static Map.Entry<Felt, Felt> prepareResourceBoundsForFee(ResourceBoundsMapping resourceBounds) {
        BigInteger l1GasBound = L1_GAS_PREFIX.getValue().shiftLeft(64 + 128)
                .add(resourceBounds.getL1Gas().getMaxAmount().getValue().shiftLeft(128))
                .add(resourceBounds.getL1Gas().getMaxPricePerUnit().getValue());
        Felt l1GasBoundFelt = ToFeltKt.getToFelt(l1GasBound);

        BigInteger l2GasBound = L2_GAS_PREFIX.getValue().shiftLeft(64 + 128)
                .add(resourceBounds.getL2Gas().getMaxAmount().getValue().shiftLeft(128))
                .add(resourceBounds.getL2Gas().getMaxPricePerUnit().getValue());
        Felt l2GasBoundFelt = ToFeltKt.getToFelt(l2GasBound);

        return new AbstractMap.SimpleEntry<>(l1GasBoundFelt, l2GasBoundFelt);
    }


    public static Felt calculateDeployAccountV3TxHash(
            Felt classHash,
            List<Felt> constructorCalldata,
            Felt salt,
            List<Felt> paymasterData,
            StarknetChainId chainId,
            TransactionVersion version,
            Felt nonce,
            Uint64 tip,
            ResourceBoundsMapping resourceBounds,
            DAMode feeDataAvailabilityMode,
            DAMode nonceDataAvailabilityMode
    ) {
        Felt contractAddress = ContractAddressCalculator.calculateAddressFromHash(
                classHash,
                constructorCalldata,
                salt
        );
        List<Felt> commonTransactionFields = prepareCommonTransanctionV3Fields(
                TransactionType.DEPLOY_ACCOUNT,
                version,
                contractAddress,
                tip,
                resourceBounds,
                paymasterData,
                chainId,
                nonce,
                nonceDataAvailabilityMode,
                feeDataAvailabilityMode
        );
        Felt[] commonTransactionFieldsArray = commonTransactionFields.toArray(new Felt[0]);
        Felt[] allFields = new Felt[commonTransactionFieldsArray.length + 3];
        System.arraycopy(commonTransactionFieldsArray, 0, allFields, 0, commonTransactionFieldsArray.length);
        allFields[commonTransactionFieldsArray.length] = Poseidon.poseidonHash(constructorCalldata);
        allFields[commonTransactionFieldsArray.length + 1] = classHash;
        allFields[commonTransactionFieldsArray.length + 2] = salt;
        return Poseidon.poseidonHash(allFields);
    }


    public static Felt calculateDeclareV3TxHash(
            Felt classHash,
            StarknetChainId chainId,
            Felt senderAddress,
            TransactionVersion version,
            Felt nonce,
            Felt compiledClassHash,
            Uint64 tip,
            ResourceBoundsMapping resourceBounds,
            List<Felt> paymasterData,
            List<Felt> accountDeploymentData,
            DAMode feeDataAvailabilityMode,
            DAMode nonceDataAvailabilityMode
    ) {
        List<Felt> commonTransactionFields = prepareCommonTransanctionV3Fields(
                TransactionType.DECLARE,
                version,
                senderAddress,
                tip,
                resourceBounds,
                paymasterData,
                chainId,
                nonce,
                nonceDataAvailabilityMode,
                feeDataAvailabilityMode
        );
        Felt[] commonTransactionFieldsArray = commonTransactionFields.toArray(new Felt[0]);
        Felt[] allFields = new Felt[commonTransactionFieldsArray.length + 3];
        System.arraycopy(commonTransactionFieldsArray, 0, allFields, 0, commonTransactionFieldsArray.length);
        allFields[commonTransactionFieldsArray.length] = Poseidon.poseidonHash(accountDeploymentData);
        allFields[commonTransactionFieldsArray.length + 1] = classHash;
        allFields[commonTransactionFieldsArray.length + 2] = compiledClassHash;
        return Poseidon.poseidonHash(allFields);
    }
}
