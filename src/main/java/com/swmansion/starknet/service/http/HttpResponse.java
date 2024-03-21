package com.swmansion.starknet.service.http;

public class HttpResponse {
    private boolean isSuccessful;
    private int code;
    private String body;

    public HttpResponse(boolean isSuccessful, int code, String body) {
        this.isSuccessful = isSuccessful;
        this.code = code;
        this.body = body;
    }

    // getters and setters
    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
