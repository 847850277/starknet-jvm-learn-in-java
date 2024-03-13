package com.swmansion.starknet.crypto;

import com.swmansion.starknet.data.extensions.ToFeltKt;
import com.swmansion.starknet.data.types.Felt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

@Metadata(
        mv = {2, 0, 0},
        k = 2,
        xi = 2,
        d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0002\u0010\u0003¨\u0006\t"},
        d2 = {"MASK_250", "Ljava/math/BigInteger;", "getMASK_250$annotations", "()V", "keccak", "input", "", "starknetKeccak", "Lcom/swmansion/starknet/data/types/Felt;", "starknet-jvm-learn"}
)
public class Keccak {

    private static final BigInteger MASK_250;

    /** @deprecated */
    // $FF: synthetic method
    private static void getMASK_250$annotations() {
    }

    @NotNull
    public static final Felt starknetKeccak(@NotNull byte[] input) {
        Intrinsics.checkNotNullParameter(input, "input");
        BigInteger var10000 = keccak(input).and(MASK_250);
        Intrinsics.checkNotNullExpressionValue(var10000, "keccak(input).and(MASK_250)");
        return ToFeltKt.getToFelt(var10000);
    }

    @NotNull
    public static final BigInteger keccak(@NotNull byte[] input) {
        Intrinsics.checkNotNullParameter(input, "input");
        org.bouncycastle.jcajce.provider.digest.Keccak.Digest256 var2 = new org.bouncycastle.jcajce.provider.digest.Keccak.Digest256();
        //int var4 = false;
        var2.update(input);
        return new BigInteger(var2.digest());
    }

    static {
        BigInteger var10000 = BigInteger.valueOf(2L).pow(250);
        Intrinsics.checkNotNullExpressionValue(var10000, "BigInteger.valueOf(2).pow(250)");
        BigInteger var0 = var10000;
        var10000 = BigInteger.ONE;
        Intrinsics.checkNotNullExpressionValue(var10000, "BigInteger.ONE");
        BigInteger var1 = var10000;
        var10000 = var0.subtract(var1);
        Intrinsics.checkNotNullExpressionValue(var10000, "subtract(...)");
        MASK_250 = var10000;
    }
}
