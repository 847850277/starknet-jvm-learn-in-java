package com.swmansion.starknet.provider.rpc;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonRpcResponse<T> {
    @JsonProperty("id")
    private int id;

    @JsonProperty("jsonrpc")
    private String jsonRpc;

    @JsonProperty("result")
    private T result;

    @JsonProperty("error")
    private JsonRpcError error;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJsonRpc() {
        return jsonRpc;
    }

    public void setJsonRpc(String jsonRpc) {
        this.jsonRpc = jsonRpc;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public JsonRpcError getError() {
        return error;
    }

    public void setError(JsonRpcError error) {
        this.error = error;
    }
}
