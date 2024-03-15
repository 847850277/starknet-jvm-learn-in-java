package com.swmansion.starknet.signer;

import com.swmansion.starknet.crypto.StarknetCurve;
import com.swmansion.starknet.data.TypedData;
import com.swmansion.starknet.data.types.Felt;
import com.swmansion.starknet.data.types.transactions.Transaction;
import com.swmansion.starknet.signer.Signer;

import java.util.List;

public class StarkCurveSigner implements Signer {

    private Felt privateKey;
    private Felt publicKey;

    public StarkCurveSigner(Felt privateKey) {
        this.privateKey = privateKey;
        this.publicKey = StarknetCurve.getPublicKey(privateKey);
    }

    @Override
    public Felt getPublicKey() {
        return publicKey;
    }

    @Override
    public List<Felt> signTransaction(Transaction transaction) {
        if (transaction.getHash() == null) {
            throw new IllegalArgumentException("Invalid transaction: hash is missing.");
        }

        return StarknetCurve.sign(privateKey, transaction.getHash()).toList();
    }

    @Override
    public List<Felt> signTypedData(TypedData typedData, Felt accountAddress) {
        Felt messageHash = typedData.getMessageHash(accountAddress);
        return StarknetCurve.sign(privateKey, messageHash).toList();
    }
}
