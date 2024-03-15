package com.swmansion.starknet.data.types;

import com.swmansion.starknet.data.TransactionHashCalculator;
import com.swmansion.starknet.data.types.transactions.TransactionVersion;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionHashCalculatorTest {


    @Nested
    class DeprecatedTransactionHashTest {
        private List<Felt> calldata = Arrays.asList(new Felt(999), new Felt(888), new Felt(777));
        private Felt maxFee = Felt.fromHex("0xabcd987654210");
        private StarknetChainId chainId = StarknetChainId.GOERLI;

        @Test
        void calculate_invoke_v1_transaction_hash() {
            Felt hash = TransactionHashCalculator.calculateInvokeTxV1Hash(
                    Felt.fromHex("0x6352037a8acbb31095a8ed0f4aa8d8639e13b705b043a1b08f9640d2f9f0d56"),
                    calldata,
                    chainId,
                    TransactionVersion.V1,
                    new Felt(9876),
                    maxFee
            );
            Felt expected = Felt.fromHex("0x119b1a69e0c35b9035be945d3a1d551f2f78473b10311734fafb1f5df3f61d9");
            assertEquals(expected, hash);
        }

//        @Test
//        void calculate_deploy_account_v1_transaction_hash() {
//            Felt hash = TransactionHashCalculator.calculateDeployAccountV1TxHash(
//                    Felt.fromHex("0x21a7f43387573b68666669a0ed764252ce5367708e696e31967764a90b429c2"),
//                    calldata,
//                    new Felt(1234),
//                    chainId,
//                    TransactionVersion.V1,
//                    maxFee,
//                    Felt.ZERO
//            );
//            Felt expected = Felt.fromHex("0x68beaf15e356928a1850cf343be85032efad964324b0abca4a9a57ff2057ef7");
//            assertEquals(expected, hash);
//        }
//
//        @Test
//        void calculate_declare_v1_transaction_hash() {
//            Felt hash = TransactionHashCalculator.calculateDeclareV1TxHash(
//                    Felt.fromHex("0x5ae9d09292a50ed48c5930904c880dab56e85b825022a7d689cfc9e65e01ee7"),
//                    Felt.fromHex("0x6352037a8acbb31095a8ed0f4aa8d8639e13b705b043a1b08f9640d2f9f0d56"),
//                    TransactionVersion.V1,
//                    chainId,
//                    new Felt(9876),
//                    maxFee
//            );
//            Felt expected = Felt.fromHex("0x64584f4e821e8d3bcd08295cbd7675858ca9a5a882108e9a31df273e2fb320f");
//            assertEquals(expected, hash);
//        }
//
//        @Test
//        void calculate_declare_v2_transaction_hash() {
//            Felt hash = TransactionHashCalculator.calculateDeclareV2TxHash(
//                    Felt.fromHex("0x5ae9d09292a50ed48c5930904c880dab56e85b825022a7d689cfc9e65e01ee7"),
//                    Felt.fromHex("0x1add56d64bebf8140f3b8a38bdf102b7874437f0c861ab4ca7526ec33b4d0f8"),
//                    Felt.fromHex("0x6352037a8acbb31095a8ed0f4aa8d8639e13b705b043a1b08f9640d2f9f0d56"),
//                    TransactionVersion.V2,
//                    chainId,
//                    new Felt(9876),
//                    maxFee
//            );
//            Felt expected = Felt.fromHex("0x31e70aad6b93265e2bd1f619841115320a2a2899759a3af3b1d9582f23e7588");
//            assertEquals(expected, hash);
//        }

    }

}
