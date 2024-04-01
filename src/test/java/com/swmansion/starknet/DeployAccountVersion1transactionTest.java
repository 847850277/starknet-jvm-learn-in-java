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


    @Test
    public void step4() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n" +
                "  \"jsonrpc\": \"2.0\",\n" +
                "  \"method\": \"starknet_getTransactionStatus\",\n" +
                "  \"id\": 0,\n" +
                "  \"params\": {\n" +
                "    \"transaction_hash\": \"0x57d17bb3cae816f42b64b674a960523ee19b7e42122e2e9184b95b210153c80\"\n" +
                "  }\n" +
                "}");
        Request request = new Request.Builder()
                .url("http://localhost:5050/rpc")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        // Assert that the HTTP response status is 200
        assertEquals(200, response.code());
    }

    @Test
    public void step5() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n" +
                "  \"address\": \"0x4428a52af4b56b60eafba3bfe8d45f06b3ba6567db259e1f815f818632fd18f\",\n" +
                "  \"amount\": 500000000000000000000000000000,\n" +
                "  \"unit\": \"WEI\"\n" +
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
    public void step6() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n" +
                "  \"address\": \"0x4428a52af4b56b60eafba3bfe8d45f06b3ba6567db259e1f815f818632fd18f\",\n" +
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
    public void step7() throws IOException {
        String command = "sncast --json --path-to-scarb-toml /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/src/test/resources/contracts/Scarb.toml --accounts-file /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/src/test/resources/accounts/standard_account_test/starknet_open_zeppelin_accounts.json --url http://127.0.0.1:5050/rpc --account __default__ account deploy --name standard_account_test --max-fee 0x38d7ea4c68000 --class-hash 0x4d07e40e93398ed3c76981e72dd1fd22557a78ce36c0515f679e27f0bb5bc5f";

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

    @Test
    public void step8() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n" +
                "  \"jsonrpc\": \"2.0\",\n" +
                "  \"method\": \"starknet_getTransactionStatus\",\n" +
                "  \"id\": 0,\n" +
                "  \"params\": {\n" +
                "    \"transaction_hash\": \"0x1cbe432a0c94353fd0e412626faed02cc7adb5098ba5d899d4a0743258d071a\"\n" +
                "  }\n" +
                "}");
        Request request = new Request.Builder()
                .url("http://localhost:5050/rpc")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        // Assert that the HTTP response status is 200
        assertEquals(200, response.code());
    }


    @Test
    public void step9() throws IOException {
        String command = "sncast --json --path-to-scarb-toml /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/src/test/resources/contracts/Scarb.toml --accounts-file /Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn/src/test/resources/accounts/standard_account_test/starknet_open_zeppelin_accounts.json --url http://127.0.0.1:5050/rpc --account __default__ deploy --class-hash 0x219d203dcacab933706bf9426a446c5082a983ed2044992fb0035a7aea9643d --max-fee 0x38d7ea4c68000 --constructor-calldata 0x1c3";

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

    @Test
    public void step10() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n" +
                "  \"jsonrpc\": \"2.0\",\n" +
                "  \"method\": \"starknet_getTransactionStatus\",\n" +
                "  \"id\": 0,\n" +
                "  \"params\": {\n" +
                "    \"transaction_hash\": \"0x15377df31988a0f3dcc6472e75686bcc6446d21fbbe1d7f723d0af54e13e990\"\n" +
                "  }\n" +
                "}");
        Request request = new Request.Builder()
                .url("http://localhost:5050/rpc")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        // Assert that the HTTP response status is 200
        assertEquals(200, response.code());
    }

    @Test
    public void step11() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n" +
                "  \"jsonrpc\": \"2.0\",\n" +
                "  \"method\": \"starknet_chainId\",\n" +
                "  \"id\": 0,\n" +
                "  \"params\": []\n" +
                "}");
        Request request = new Request.Builder()
                .url("http://localhost:5050/rpc")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        // Assert that the HTTP response status is 200
        assertEquals(200, response.code());
    }

    @Test
    public void step12() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n" +
                "  \"address\": \"0x3163a5430647ec0387aac4958666e0640948f77434a1adbdbf31a2e867f7c0a\",\n" +
                "  \"amount\": 500000000000000000000000000000,\n" +
                "  \"unit\": \"WEI\"\n" +
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
    public void step13() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n" +
                "  \"jsonrpc\": \"2.0\",\n" +
                "  \"method\": \"starknet_addDeployAccountTransaction\",\n" +
                "  \"id\": 0,\n" +
                "  \"params\": {\n" +
                "    \"deploy_account_transaction\": {\n" +
                "      \"class_hash\": \"0x4d07e40e93398ed3c76981e72dd1fd22557a78ce36c0515f679e27f0bb5bc5f\",\n" +
                "      \"contract_address_salt\": \"0x1\",\n" +
                "      \"constructor_calldata\": [\n" +
                "        \"0x6ab9e9e75f5a5d90796f155d58671a6a038e47e15643383e3205e806410050e\"\n" +
                "      ],\n" +
                "      \"version\": \"0x1\",\n" +
                "      \"nonce\": \"0x0\",\n" +
                "      \"max_fee\": \"0x11fcc58c7f7000\",\n" +
                "      \"signature\": [\n" +
                "        \"0x1d5542b40fe675b42786ae06dd36b28c9092e9401ebe9d68a9cbd9a9a6c4007\",\n" +
                "        \"0x3ac3f6ac7bffcdaddd0aa3d9ce2d852f9a4bc922b15e3c75ed987730bc45168\"\n" +
                "      ],\n" +
                "      \"type\": \"DEPLOY_ACCOUNT\"\n" +
                "    }\n" +
                "  }\n" +
                "}\n");
        Request request = new Request.Builder()
                .url("http://localhost:5050/rpc")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        // Assert that the HTTP response status is 200
        assertEquals(200, response.code());
    }

    @Test
    public void step14() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n" +
                "  \"jsonrpc\": \"2.0\",\n" +
                "  \"method\": \"starknet_getTransactionByHash\",\n" +
                "  \"id\": 0,\n" +
                "  \"params\": {\n" +
                "    \"transaction_hash\": \"0x3e6d4a3822bd3be59c2f562109b1acda6365e2c3d7a79e7e925b31ed020c77f\"\n" +
                "  }\n" +
                "}");
        Request request = new Request.Builder()
                .url("http://localhost:5050/rpc")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        // Assert that the HTTP response status is 200
        assertEquals(200, response.code());
    }

    @Test
    public void step15() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n" +
                "  \"jsonrpc\": \"2.0\",\n" +
                "  \"method\": \"starknet_getNonce\",\n" +
                "  \"id\": 0,\n" +
                "  \"params\": {\n" +
                "    \"contract_address\": \"0x3163a5430647ec0387aac4958666e0640948f77434a1adbdbf31a2e867f7c0a\",\n" +
                "    \"block_id\": \"pending\"\n" +
                "  }\n" +
                "}");
        Request request = new Request.Builder()
                .url("http://localhost:5050/rpc")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        // Assert that the HTTP response status is 200
        assertEquals(200, response.code());
    }


    @Test
    public void step16() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n" +
                "  \"jsonrpc\": \"2.0\",\n" +
                "  \"method\": \"starknet_estimateFee\",\n" +
                "  \"id\": 0,\n" +
                "  \"params\": {\n" +
                "    \"request\": [\n" +
                "      {\n" +
                "        \"sender_address\": \"0x3163a5430647ec0387aac4958666e0640948f77434a1adbdbf31a2e867f7c0a\",\n" +
                "        \"calldata\": [\n" +
                "          \"0x1\",\n" +
                "          \"0x14e04886265086e2480a07796e258b4564dd621dc1571316e08e9a5bb6230e0\",\n" +
                "          \"0x362398bec32bc0ebb411203221a35a0301193a96f317ebe5e40be9f60d15320\",\n" +
                "          \"0x0\",\n" +
                "          \"0x1\",\n" +
                "          \"0x1\",\n" +
                "          \"0xa\"\n" +
                "        ],\n" +
                "        \"signature\": [\n" +
                "          \"0x612e12ced75bb34b6a37d114ba7c4b038cc6b4ef4df93e8399e40ca9787fa12\",\n" +
                "          \"0x4230bb429164b114acab7b61367a7dbfa537ab6edaf8421dd186672183811b3\"\n" +
                "        ],\n" +
                "        \"max_fee\": \"0x0\",\n" +
                "        \"version\": \"0x100000000000000000000000000000001\",\n" +
                "        \"nonce\": \"0x1\",\n" +
                "        \"type\": \"INVOKE\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"simulation_flags\": [],\n" +
                "    \"block_id\": \"pending\"\n" +
                "  }\n" +
                "}");
        Request request = new Request.Builder()
                .url("http://localhost:5050/rpc")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        // Assert that the HTTP response status is 200
        assertEquals(200, response.code());
    }


    @Test
    public void step17() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n" +
                "  \"jsonrpc\": \"2.0\",\n" +
                "  \"method\": \"starknet_getNonce\",\n" +
                "  \"id\": 0,\n" +
                "  \"params\": {\n" +
                "    \"contract_address\": \"0x3163a5430647ec0387aac4958666e0640948f77434a1adbdbf31a2e867f7c0a\",\n" +
                "    \"block_id\": \"pending\"\n" +
                "  }\n" +
                "}");
        Request request = new Request.Builder()
                .url("http://localhost:5050/rpc")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        // Assert that the HTTP response status is 200
        assertEquals(200, response.code());
    }

    @Test
    public void step18() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n" +
                "  \"jsonrpc\": \"2.0\",\n" +
                "  \"method\": \"starknet_addInvokeTransaction\",\n" +
                "  \"id\": 0,\n" +
                "  \"params\": {\n" +
                "    \"invoke_transaction\": {\n" +
                "      \"type\": \"INVOKE\",\n" +
                "      \"sender_address\": \"0x3163a5430647ec0387aac4958666e0640948f77434a1adbdbf31a2e867f7c0a\",\n" +
                "      \"calldata\": [\n" +
                "        \"0x1\",\n" +
                "        \"0x14e04886265086e2480a07796e258b4564dd621dc1571316e08e9a5bb6230e0\",\n" +
                "        \"0x362398bec32bc0ebb411203221a35a0301193a96f317ebe5e40be9f60d15320\",\n" +
                "        \"0x0\",\n" +
                "        \"0x1\",\n" +
                "        \"0x1\",\n" +
                "        \"0xa\"\n" +
                "      ],\n" +
                "      \"signature\": [\n" +
                "        \"0x1631a12dc8e8ef417da0cb10fce64194322634756969564452681994802aee7\",\n" +
                "        \"0x7ed490cd409bc5ad4cc458067857ea6daf518e14f7e36357d57c84d176a2e6\"\n" +
                "      ],\n" +
                "      \"max_fee\": \"0x24f890cf6400\",\n" +
                "      \"version\": \"0x1\",\n" +
                "      \"nonce\": \"0x1\"\n" +
                "    }\n" +
                "  }\n" +
                "}");
        Request request = new Request.Builder()
                .url("http://localhost:5050/rpc")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        // Assert that the HTTP response status is 200
        assertEquals(200, response.code());
    }

    @Test
    public void step19() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n" +
                "  \"jsonrpc\": \"2.0\",\n" +
                "  \"method\": \"starknet_getTransactionReceipt\",\n" +
                "  \"id\": 0,\n" +
                "  \"params\": {\n" +
                "    \"transaction_hash\": \"0x6987ea37e2821258d741e30c504e4ebc7ae9464c9719cf97c2cb6f4b4532a10\"\n" +
                "  }\n" +
                "}");
        Request request = new Request.Builder()
                .url("http://localhost:5050/rpc")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        // Assert that the HTTP response status is 200
        assertEquals(200, response.code());
    }
}
