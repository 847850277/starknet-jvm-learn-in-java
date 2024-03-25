package com.swmansion.starknet.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import okhttp3.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MintTest {

    @Data
    public static class MintResponse {
        private String new_balance;
        private String unit;
        private String tx_hash;

        // getters and setters...


    }

    @Test
    public void mintEthTest() throws IOException {
        OkHttpClient client = new OkHttpClient();
        ObjectMapper mapper = new ObjectMapper();

        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        String jsonInputString = "{"
                + "\"address\": \"0x4428a52af4b56b60eafba3bfe8d45f06b3ba6567db259e1f815f818632fd18f\","
                + "\"amount\": 500000000000000000000000000000,"
                + "\"unit\": \"WEI\""
                + "}";
        RequestBody body = RequestBody.create(jsonInputString, JSON);

        Request request = new Request.Builder()
                .url("http://127.0.0.1:5050/mint")
                .post(body)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            MintResponse mintResponse = mapper.readValue(responseBody, MintResponse.class);
            //if run times over once，the result over 500000000000000000000000000000
            assertEquals("500000000000000000000000000000", mintResponse.getNew_balance());
            assertEquals("WEI", mintResponse.getUnit());
            // You may want to check the tx_hash as well, but it's likely to be different every time.
        }
    }

    @Test
    public void mintFriTest() throws IOException {
        OkHttpClient client = new OkHttpClient();
        ObjectMapper mapper = new ObjectMapper();

        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        String jsonInputString = "{"
                + "\"address\": \"0x4428a52af4b56b60eafba3bfe8d45f06b3ba6567db259e1f815f818632fd18f\","
                + "\"amount\": 500000000000000000000000000000,"
                + "\"unit\": \"FRI\""
                + "}";
        RequestBody body = RequestBody.create(jsonInputString, JSON);

        Request request = new Request.Builder()
                .url("http://127.0.0.1:5050/mint")
                .post(body)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            MintResponse mintResponse = mapper.readValue(responseBody, MintResponse.class);
            assertEquals("500000000000000000000000000000", mintResponse.getNew_balance());
            assertEquals("FRI", mintResponse.getUnit());
            // You may want to check the tx_hash as well, but it's likely to be different every time.
        }
    }


}
