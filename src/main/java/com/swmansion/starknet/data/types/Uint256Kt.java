package com.swmansion.starknet.data.types;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

import java.math.BigInteger;

@Metadata(
        mv = {2, 0, 0},
        k = 2,
        xi = 2,
        d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0004"},
        d2 = {"SHIFT", "", "SHIFT_MOD", "Ljava/math/BigInteger;", "starknet-jvm-learn"}
)
public class Uint256Kt {

    private static final int SHIFT = 128;
    private static final BigInteger SHIFT_MOD;

    static {
        BigInteger var10000 = BigInteger.valueOf(2L).pow(128);
        Intrinsics.checkNotNullExpressionValue(var10000, "BigInteger.valueOf(2).pow(128)");
        SHIFT_MOD = var10000;
    }

    // $FF: synthetic method
    public static final BigInteger access$getSHIFT_MOD$p() {
        return SHIFT_MOD;
    }
}
