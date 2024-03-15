package com.swmansion.starknet.data.types.transactions;

import com.swmansion.starknet.data.types.Felt;
import com.swmansion.starknet.data.types.Uint64;
import com.swmansion.starknet.data.types.ResourceBoundsMapping;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class InvokeTransactionV3Payload extends InvokeTransactionPayload implements Serializable {

    private Felt senderAddress;
    private List<Felt> calldata;
    private List<Felt> signature;
    private Felt nonce;
    private ResourceBoundsMapping resourceBounds;
    private Uint64 tip;
    private List<Felt> paymasterData;
    private List<Felt> accountDeploymentData;
    private DAMode nonceDataAvailabilityMode;
    private DAMode feeDataAvailabilityMode;
    private TransactionVersion version;

    private InvokeTransactionV3Payload(Felt senderAddress, List<Felt> calldata, List<Felt> signature, Felt nonce, ResourceBoundsMapping resourceBounds, Uint64 tip, List<Felt> paymasterData, List<Felt> accountDeploymentData, DAMode nonceDataAvailabilityMode, DAMode feeDataAvailabilityMode, TransactionVersion version) {
        this.senderAddress = senderAddress;
        this.calldata = calldata;
        this.signature = signature;
        this.nonce = nonce;
        this.resourceBounds = resourceBounds;
        this.tip = tip;
        this.paymasterData = paymasterData;
        this.accountDeploymentData = accountDeploymentData;
        this.nonceDataAvailabilityMode = nonceDataAvailabilityMode;
        this.feeDataAvailabilityMode = feeDataAvailabilityMode;
        this.version = version;
    }

    public InvokeTransactionV3Payload(Felt senderAddress, List<Felt> calldata, List<Felt> signature, Felt nonce, ResourceBoundsMapping resourceBounds, TransactionVersion version) {
        this(senderAddress, calldata, signature, nonce, resourceBounds, Uint64.ZERO, Collections.emptyList(), Collections.emptyList(), DAMode.L1, DAMode.L1, version);
    }

    public Felt getSenderAddress() {
        return senderAddress;
    }

    public List<Felt> getCalldata() {
        return calldata;
    }

    public List<Felt> getSignature() {
        return signature;
    }

    public Felt getNonce() {
        return nonce;
    }

    public ResourceBoundsMapping getResourceBounds() {
        return resourceBounds;
    }

    public Uint64 getTip() {
        return tip;
    }

    public List<Felt> getPaymasterData() {
        return paymasterData;
    }

    public List<Felt> getAccountDeploymentData() {
        return accountDeploymentData;
    }

    public DAMode getNonceDataAvailabilityMode() {
        return nonceDataAvailabilityMode;
    }

    public DAMode getFeeDataAvailabilityMode() {
        return feeDataAvailabilityMode;
    }

    public TransactionVersion getVersion() {
        return version;
    }
}
