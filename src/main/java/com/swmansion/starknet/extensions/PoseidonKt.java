package com.swmansion.starknet.extensions;

import java.math.BigInteger;

public class PoseidonKt {

    public static long[] splitBigInteger(BigInteger bigInt) {
        long[] result = new long[4];

        // if the input BigInteger is zero, return an array of zeros
        if (bigInt.equals(BigInteger.ZERO)) {
            return result;
        }
        // mask has all bits set to 1 except the least significant one
        BigInteger mask = BigInteger.valueOf(2).pow(64).subtract(BigInteger.ONE);

        // loop through the 64-bit chunks of the BigInteger, shift them and store in the LongArray
        for (int i = 0; i < 4; i++) {
            result[i] = bigInt.shiftRight(i * 64).and(mask).longValue();
        }

        return result;
    }

    public static BigInteger[] toBigIntegerArray(long[] longArray) {
        BigInteger[] result = new BigInteger[longArray.length];

        for (int i = 0; i < longArray.length; i++) {
            result[i] = BigInteger.valueOf(Long.parseUnsignedLong(String.valueOf(longArray[i])));
        }

        return result;
    }

    public static BigInteger unsplitBigInteger(long[] arr) {
        BigInteger[] powersOfTwo = {
                BigInteger.valueOf(2).pow(0),
                BigInteger.valueOf(2).pow(64),
                BigInteger.valueOf(2).pow(128),
                BigInteger.valueOf(2).pow(192),
        };
        BigInteger[] barr = toBigIntegerArray(arr);
        BigInteger result = BigInteger.ZERO;

        for (int i = 0; i < barr.length; i++) {
            result = result.add(barr[i].multiply(powersOfTwo[i]));
        }

        return result;
    }
}
