package com.swmansion.starknet.extensions;

import com.swmansion.starknet.data.types.Felt;

import java.math.BigInteger;
import java.util.Arrays;

public class StarknetCurveKt {

    public static byte[] bigintToNative(BigInteger input) {
        byte[] converted = toBytes(input);
        reverse(converted);
        if (converted.length == 32) {
            return converted;
        }
        return Arrays.copyOf(converted, 32);
    }

    public static void reverse(byte[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            byte temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            left++;
            right--;
        }
    }

    private static byte[] toBytes(BigInteger value) {
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

    public static byte[] feltToNative(Felt input) {
        return bigintToNative(input.getValue());
    }
}
