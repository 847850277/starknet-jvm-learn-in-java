package com.swmansion.starknet.data.types.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.swmansion.starknet.data.Event;
import com.swmansion.starknet.data.types.FeePayment;
import com.swmansion.starknet.data.types.Felt;
import com.swmansion.starknet.data.types.MessageL2ToL1;
import lombok.Data;

import java.util.List;

@Data
public abstract class TransactionReceipt {

    @JsonProperty("hash")
    private Felt hash;

    @JsonProperty("type")
    private TransactionType type;

    @JsonProperty("actualFee")
    private FeePayment actualFee;

    @JsonProperty("executionStatus")
    private TransactionExecutionStatus executionStatus;

    @JsonProperty("finalityStatus")
    private TransactionFinalityStatus finalityStatus;

    @JsonProperty("revertReason")
    private String revertReason;

    @JsonProperty("events")
    private List<Event> events;

    @JsonProperty("messagesSent")
    private List<MessageL2ToL1> messagesSent;

    @JsonProperty("executionResources")
    private ExecutionResources executionResources;

    @JsonProperty("blockHash")
    private Felt blockHash;

    @JsonProperty("blockNumber")
    private Integer blockNumber;

    public boolean isAccepted() {
        return executionStatus == TransactionExecutionStatus.SUCCEEDED &&
                (finalityStatus == TransactionFinalityStatus.ACCEPTED_ON_L1 || finalityStatus == TransactionFinalityStatus.ACCEPTED_ON_L2);
    }

    public boolean isPending() {
        return !hasBlockInfo() && finalityStatus == TransactionFinalityStatus.ACCEPTED_ON_L2;
    }

    public boolean hasBlockInfo() {
        return blockHash != null && blockNumber != null;
    }

    // getters and setters
}
