package com.swmansion.starknet.service.http;

public class RpcRequestFailedException extends RequestFailedException {
    private int code;

    public RpcRequestFailedException(int code, String message, String payload) {
        this(code, message, null, payload);
    }

    public RpcRequestFailedException(int code, String message, String data, String payload) {
        super(message, data, payload);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
