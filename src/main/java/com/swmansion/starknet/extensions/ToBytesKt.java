package com.swmansion.starknet.extensions;

import java.math.BigInteger;

public class ToBytesKt {

    public static byte[] toBytes(BigInteger value) {
        if (value.signum() == -1) {
            throw new IllegalArgumentException("Creating ByteArray from negative numbers is not supported.");
        }
        int bitLength = value.bitLength();
        if (bitLength != 0 && bitLength % 8 == 0) {
            byte[] byteArray = value.toByteArray();
            byte[] result = new byte[byteArray.length - 1];
            System.arraycopy(byteArray, 1, result, 0, result.length);
            return result;
        }
        return value.toByteArray();
    }

}
