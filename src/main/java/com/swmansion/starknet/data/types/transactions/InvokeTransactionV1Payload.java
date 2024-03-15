package com.swmansion.starknet.data.types.transactions;

import com.swmansion.starknet.data.types.Felt;

import java.io.Serializable;
import java.util.List;

public class InvokeTransactionV1Payload implements Serializable {

    private Felt senderAddress;
    private List<Felt> calldata;
    private List<Felt>  signature;
    private Felt maxFee;
    private TransactionVersion version;
    private Felt nonce;

    public InvokeTransactionV1Payload(Felt senderAddress, List<Felt>  calldata, List<Felt>  signature, Felt maxFee, TransactionVersion version, Felt nonce) {
        this.senderAddress = senderAddress;
        this.calldata = calldata;
        this.signature = signature;
        this.maxFee = maxFee;
        this.version = version;
        this.nonce = nonce;
    }

    public Felt getSenderAddress() {
        return senderAddress;
    }

    public List<Felt>  getCalldata() {
        return calldata;
    }

    public List<Felt>  getSignature() {
        return signature;
    }

    public Felt getMaxFee() {
        return maxFee;
    }

    public TransactionVersion getVersion() {
        return version;
    }

    public Felt getNonce() {
        return nonce;
    }
}
