package com.swmansion.starknet.extensions;

import java.math.BigInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
        mv = {2, 0, 0},
        k = 2,
        xi = 2,
        d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"},
        d2 = {"toHex", "", "Ljava/math/BigInteger;", "starknet-jvm-learn"}
)
public final class ToHexKt {

    public static final String toHex(BigInteger $this$toHex) {
        Intrinsics.checkNotNullParameter($this$toHex, "$this$toHex");
        return "0x" + $this$toHex.toString(16);
    }
}
