package com.swmansion.starknet.data;

import java.math.BigInteger;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(
        mv = {2, 0, 0},
        k = 2,
        xi = 2,
        d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0004"},
        d2 = {"parseHex", "Ljava/math/BigInteger;", "value", "", "starknet-jvm-learn"}
)
//@JvmName(name = "ParseHex")
public final class ParseHex {

    public static final BigInteger parseHex(String value){
        Intrinsics.checkNotNullParameter(value, "value");
        if (!StringsKt.startsWith(value, "0x", false)) {
            throw new IllegalArgumentException("Hex must start with 0x");
        } else {
            return new BigInteger(StringsKt.removePrefix(value, (CharSequence)"0x"), 16);
        }
    }
}
