package com.swmansion.starknet.api;

import lombok.Data;
import okhttp3.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TransactionStatusTest {

    @Data
    public static class TransactionStatusResponse {
        private String jsonrpc;
        private int id;
        private Result result;


        @Data
        public static class Result {
            // Define the fields in the result object here

            // getters and setters...
        }
    }

    @Test
    public void getTransactionStatusTest() throws IOException {
        OkHttpClient client = new OkHttpClient();

        String transactionHash = "0x1cbe432a0c94353fd0e412626faed02cc7adb5098ba5d899d4a0743258d071a";

        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        String jsonInputString = "{"
                + "\"jsonrpc\": \"2.0\","
                + "\"method\": \"starknet_getTransactionStatus\","
                + "\"id\": 0,"
                + "\"params\": {"
                + "\"transaction_hash\": \""+transactionHash+"\""
                + "}"
                + "}";
        RequestBody body = RequestBody.create(jsonInputString, JSON);

        Request request = new Request.Builder()
                .url("http://127.0.0.1:5050/rpc")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            System.out.println(responseBody);
        }
    }



}
