package com.swmansion.starknet.provider.rpc;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonRpcError {
    @JsonProperty("code")
    private int code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private String data;

    // Getters and setters
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
