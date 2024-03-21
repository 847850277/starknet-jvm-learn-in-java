package com.swmansion.starknet.service.http;

public class RequestFailedException extends RuntimeException {
    private String data;
    private String payload;

    public RequestFailedException(String message, String payload) {
        this(message, null, payload);
    }

    public RequestFailedException(String message, String data, String payload) {
        super(message);
        this.data = data;
        this.payload = payload;
    }

    @Override
    public String toString() {
        if (data == null) {
            return getMessage() + ": " + payload;
        } else {
            return getMessage() + ": " + data + " : " + payload;
        }
    }
}
