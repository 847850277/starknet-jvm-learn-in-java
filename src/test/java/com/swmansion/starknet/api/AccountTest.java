package com.swmansion.starknet.api;

public class AccountTest {

    public static void main(String[] args) {
        String url = "curl --location 'http://127.0.0.1:5050/mint' \\\n" +
                "--header 'accept: application/json' \\\n" +
                "--header 'content-type: application/json' \\\n" +
                "--data '{\n" +
                "\"address\": \"0x00998f1Aa20CB1D57da61F5dae2bF5F35BCfB42D41c7bb9E33D3D281a1cE3b25\",\n" +
                "\"amount\": 500000000000000000000000000000,\n" +
                "\"unit\": \"WEI\"\n" +
                "}'"
    }

}




