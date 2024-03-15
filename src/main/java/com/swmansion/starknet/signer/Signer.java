package com.swmansion.starknet.signer;

import com.swmansion.starknet.data.TypedData;
import com.swmansion.starknet.data.types.Felt;
import com.swmansion.starknet.data.types.transactions.Transaction;

import java.util.List;

public interface Signer {

    /**
     * Sign a transaction
     *
     * @param transaction a transaction to be signed
     * @return a signature of provided transaction
     */
    List<Felt> signTransaction(Transaction transaction);

    /**
     * Sign TypedData.
     *
     * @param typedData TypedData instance to sign
     * @param accountAddress Account address used in the TypedData hash calculation
     * @return a signature of provided typedData
     */
    List<Felt> signTypedData(TypedData typedData, Felt accountAddress);

    /**
     * Public key used by a signer.
     */
    Felt getPublicKey();
}
