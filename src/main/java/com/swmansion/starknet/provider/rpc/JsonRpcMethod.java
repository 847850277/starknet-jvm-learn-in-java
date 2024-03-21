package com.swmansion.starknet.provider.rpc;

public enum JsonRpcMethod {
    GET_SPEC_VERSION("starknet_specVersion"),
    CALL("starknet_call"),
    INVOKE_TRANSACTION("starknet_addInvokeTransaction"),
    GET_STORAGE_AT("starknet_getStorageAt"),
    GET_CLASS("starknet_getClass"),
    GET_CLASS_AT("starknet_getClassAt"),
    GET_CLASS_HASH_AT("starknet_getClassHashAt"),
    GET_TRANSACTION_BY_HASH("starknet_getTransactionByHash"),
    GET_TRANSACTION_RECEIPT("starknet_getTransactionReceipt"),
    GET_TRANSACTION_STATUS("starknet_getTransactionStatus"),
    DECLARE("starknet_addDeclareTransaction"),
    GET_EVENTS("starknet_getEvents"),
    GET_BLOCK_NUMBER("starknet_blockNumber"),
    GET_BLOCK_HASH_AND_NUMBER("starknet_blockHashAndNumber"),
    GET_BLOCK_TRANSACTION_COUNT("starknet_getBlockTransactionCount"),
    GET_SYNCING("starknet_syncing"),
    GET_CHAIN_ID("starknet_chainId"),
    ESTIMATE_FEE("starknet_estimateFee"),
    ESTIMATE_MESSAGE_FEE("starknet_estimateMessageFee"),
    GET_BLOCK_WITH_TXS("starknet_getBlockWithTxs"),
    GET_BLOCK_WITH_TX_HASHES("starknet_getBlockWithTxHashes"),
    GET_BLOCK_WITH_RECEIPTS("starknet_getBlockWithReceipts"),
    GET_STATE_UPDATE("starknet_getStateUpdate"),
    GET_TRANSACTION_BY_BLOCK_ID_AND_INDEX("starknet_getTransactionByBlockIdAndIndex"),
    GET_NONCE("starknet_getNonce"),
    DEPLOY_ACCOUNT_TRANSACTION("starknet_addDeployAccountTransaction"),
    SIMULATE_TRANSACTIONS("starknet_simulateTransactions");

    private String methodName;

    JsonRpcMethod(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }
}