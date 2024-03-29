package com.swmansion.starknet;

import okhttp3.*;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeployAccountVersion1transactionTest {

    @Test
    public void step1() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n  \"address\": \"0x236ca5856fe740e51f8ae1e9d3cdfeb6a27c32076022cf66cda2f13a54b5264\",\n  \"amount\": 500000000000000000000000000000,\n  \"unit\": \"WEI\"\n}");
        Request request = new Request.Builder()
                .url("http://localhost:5050/mint")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        // Assert that the HTTP response status is 200
        assertEquals(200, response.code());
    }

    @Test
    public void step2() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n" +
                "  \"address\": \"0x236ca5856fe740e51f8ae1e9d3cdfeb6a27c32076022cf66cda2f13a54b5264\",\n" +
                "  \"amount\": 500000000000000000000000000000,\n" +
                "  \"unit\": \"FRI\"\n" +
                "}");
        Request request = new Request.Builder()
                .url("http://localhost:5050/mint")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        // Assert that the HTTP response status is 200
        assertEquals(200, response.code());
    }

    @Test
    public void step3() throws IOException {
        String command = "sncast --json --path-to-scarb-toml /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/src/test/resources/contracts/Scarb.toml --accounts-file /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/src/test/resources/accounts/standard_account_test/starknet_open_zeppelin_accounts.json --url http://127.0.0.1:5050/rpc --account __default__ account deploy --name __default__ --max-fee 0x38d7ea4c68000 --class-hash 0x4d07e40e93398ed3c76981e72dd1fd22557a78ce36c0515f679e27f0bb5bc5f";

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "-c", command);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
