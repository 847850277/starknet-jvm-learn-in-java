package com.swmansion.starknet.crypto;

import com.swmansion.starknet.data.types.Felt;
import com.swmansion.starknet.extensions.PoseidonKt;
import com.swmansion.starknet.extensions.ToFeltKt;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Poseidon {

    private static final int m = 3;
    private static final int r = 2;

    static {
        //System.loadLibrary("poseidon");
        //System.loadLibrary("poseidon_jni");

        System.load("/Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn-in-java/crypto/poseidon/poseidon/libposeidon.dylib");
        System.load("/Users/zhengpeng/Work/Source/Code/847850277/starknet-jvm-learn-in-java/crypto/poseidon/bindings/libposeidon_jni.dylib");
    }

    private static native long[][] hades(long[][] values);

    public static Felt poseidonHash(Felt x) {
        return ToFeltKt.getToFelt(PoseidonKt.unsplitBigInteger(
                hades(
                        new long[][]{
                                PoseidonKt.splitBigInteger(new BigInteger(x.decString())),
                                new long[]{0, 0, 0, 0},
                                new long[]{1, 0, 0, 0},
                        }
                )[0]
        ));
    }

    public static Felt poseidonHash(Felt x, Felt y) {
        return ToFeltKt.getToFelt(PoseidonKt.unsplitBigInteger(
                hades(
                        new long[][]{
                                PoseidonKt.splitBigInteger(new BigInteger(x.decString())),
                                PoseidonKt.splitBigInteger(new BigInteger(y.decString())),
                                new long[]{2, 0, 0, 0},
                        }
                )[0]
        ));
    }

    public static Felt poseidonHash(List<Felt> values) {
        List<Felt> inputValues = new ArrayList<>(values);
        inputValues.add(Felt.ONE);
        if (inputValues.size() % r == 1) inputValues.add(Felt.ZERO);

        long[][] state = new long[m][4];

        for (int iter = 0; iter < inputValues.size(); iter += 2) {
            state = hades(
                    new long[][]{
                            PoseidonKt.splitBigInteger(PoseidonKt.unsplitBigInteger(state[0]).add(new BigInteger(inputValues.get(iter).decString()))),
                            PoseidonKt.splitBigInteger(PoseidonKt.unsplitBigInteger(state[1]).add(new BigInteger(inputValues.get(iter + 1).decString()))),
                            state[2],
                    }
            );
        }

        return ToFeltKt.getToFelt(PoseidonKt.unsplitBigInteger(state[0]));
    }

    public static Felt poseidonHash(Felt... values) {
        return poseidonHash(Arrays.asList(values));
    }
}
