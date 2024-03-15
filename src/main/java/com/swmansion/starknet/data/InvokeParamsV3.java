package com.swmansion.starknet.data;

import com.swmansion.starknet.data.types.*;
import com.swmansion.starknet.data.types.transactions.DAMode;

import java.util.Collections;
import java.util.List;

public class InvokeParamsV3 extends ParamsV3 {

    private Felt nonce;
    private ResourceBoundsMapping resourceBounds;
    private Uint64 tip;
    private List<Felt> paymasterData;
    private List<Felt> accountDeploymentData;
    private DAMode nonceDataAvailabilityMode;
    private DAMode feeDataAvailabilityMode;

    private InvokeParamsV3(Felt nonce, ResourceBoundsMapping resourceBounds, Uint64 tip, List<Felt> paymasterData, List<Felt> accountDeploymentData, DAMode nonceDataAvailabilityMode, DAMode feeDataAvailabilityMode) {
        this.nonce = nonce;
        this.resourceBounds = resourceBounds;
        this.tip = tip;
        this.paymasterData = paymasterData;
        this.accountDeploymentData = accountDeploymentData;
        this.nonceDataAvailabilityMode = nonceDataAvailabilityMode;
        this.feeDataAvailabilityMode = feeDataAvailabilityMode;
    }

    public InvokeParamsV3(Felt nonce, ResourceBounds l1ResourceBounds) {
        this(nonce, new ResourceBoundsMapping(l1ResourceBounds), Uint64.ZERO, Collections.emptyList(), Collections.emptyList(), DAMode.L1, DAMode.L1);
    }

    @Override
    public Felt getNonce() {
        return nonce;
    }

    @Override
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

    @Override
    public DAMode getNonceDataAvailabilityMode() {
        return nonceDataAvailabilityMode;
    }

    @Override
    public DAMode getFeeDataAvailabilityMode() {
        return feeDataAvailabilityMode;
    }
}
