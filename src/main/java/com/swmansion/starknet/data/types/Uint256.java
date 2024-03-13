package com.swmansion.starknet.data.types;

import com.swmansion.starknet.data.ParseHex;
import com.swmansion.starknet.data.extensions.ToHexKt;
import com.swmansion.starknet.data.types.conversions.ConvertibleToCalldata;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigInteger;
import java.util.List;

public class Uint256 extends NumAsHexBase implements ConvertibleToCalldata {

    @NotNull
    private final BigInteger value;
    @JvmField
    @NotNull
    public static final BigInteger MAX;
    @JvmField
    @NotNull
    public static final Uint256 ZERO;
    @JvmField
    @NotNull
    public static final Uint256 ONE;
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker)null);

    @NotNull
    public final Felt getLow() {
        BigInteger var10002 = this.getValue().mod(Uint256Kt.access$getSHIFT_MOD$p());
        Intrinsics.checkNotNullExpressionValue(var10002, "value.mod(SHIFT_MOD)");
        return new Felt(var10002);
    }

    @NotNull
    public final Felt getHigh() {
        BigInteger var10002 = this.getValue().shiftRight(128);
        Intrinsics.checkNotNullExpressionValue(var10002, "value.shiftRight(SHIFT)");
        return new Felt(var10002);
    }

    @NotNull
    public List toCalldata() {
        return CollectionsKt.listOf(new Felt[]{this.getLow(), this.getHigh()});
    }

    @NotNull
    public String toString() {
        return "Uint256(" + this.getValue() + ')';
    }

    @NotNull
    public String hexString() {
        return ToHexKt.toHex(this.getValue());
    }

    @NotNull
    public String decString() {
        String var10000 = this.getValue().toString(10);
        Intrinsics.checkNotNullExpressionValue(var10000, "value.toString(10)");
        return var10000;
    }

    @NotNull
    public BigInteger getValue() {
        return this.value;
    }

    public Uint256(@NotNull BigInteger value) {
        super(value, (DefaultConstructorMarker)null);
        Intrinsics.checkNotNullParameter(value, "value");
        this.value = value;
        if (this.getValue().compareTo(BigInteger.ZERO) < 0) {
                throw new IllegalArgumentException("Default Uint256 constructor does not accept negative numbers, [" + this.getValue() + "] given.");
        } else if (this.getValue().compareTo(MAX) > 0) {
                throw new IllegalArgumentException("Default Uint256 constructor does not accept numbers higher than 2^256-1, [" + this.getValue() + "] given.");
        }
    }

//    public Uint256(long value) {
//        BigInteger var10001 = BigInteger.valueOf(value);
//        Intrinsics.checkNotNullExpressionValue(var10001, "BigInteger.valueOf(value)");
//        this(var10001);
//    }
//
//    public Uint256(int value) {
//        BigInteger var10001 = BigInteger.valueOf((long)value);
//        Intrinsics.checkNotNullExpressionValue(var10001, "BigInteger.valueOf(value.toLong())");
//        this(var10001);
//    }
//
//    public Uint256(@NotNull Felt value) {
//        Intrinsics.checkNotNullParameter(value, "value");
//        this(value.getValue());
//    }

    public Uint256(@NotNull BigInteger low, @NotNull BigInteger high) {
        this(low.add(high.shiftLeft(128)));
        Intrinsics.checkNotNullParameter(low, "low");
        Intrinsics.checkNotNullParameter(high, "high");
        BigInteger var10001 = low.add(high.shiftLeft(128));
        Intrinsics.checkNotNullExpressionValue(var10001, "low.add(high.shiftLeft(SHIFT))");
    }

    public Uint256(@NotNull Felt low, @NotNull Felt high) {
        this(low.getValue(), high.getValue());
        Intrinsics.checkNotNullParameter(low, "low");
        Intrinsics.checkNotNullParameter(high, "high");
    }

    static {
        BigInteger var10000 = BigInteger.valueOf(2L).pow(256);
        Intrinsics.checkNotNullExpressionValue(var10000, "BigInteger.valueOf(2).pow(256)");
        BigInteger var0 = var10000;
        var10000 = BigInteger.ONE;
        Intrinsics.checkNotNullExpressionValue(var10000, "BigInteger.ONE");
        BigInteger var1 = var10000;
        var10000 = var0.subtract(var1);
        Intrinsics.checkNotNullExpressionValue(var10000, "subtract(...)");
        MAX = var10000;
        BigInteger var10002 = BigInteger.ZERO;
        Intrinsics.checkNotNullExpressionValue(var10002, "BigInteger.ZERO");
        ZERO = new Uint256(var10002);
        var10002 = BigInteger.ONE;
        Intrinsics.checkNotNullExpressionValue(var10002, "BigInteger.ONE");
        ONE = new Uint256(var10002);
    }

    @NotNull
    public final BigInteger component1() {
        return this.getValue();
    }

    @NotNull
    public final Uint256 copy(@NotNull BigInteger value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return new Uint256(value);
    }

    // $FF: synthetic method
    public static Uint256 copy$default(Uint256 var0, BigInteger var1, int var2, Object var3) {
        if ((var2 & 1) != 0) {
            var1 = var0.getValue();
        }

        return var0.copy(var1);
    }

    public int hashCode() {
        BigInteger var10000 = this.getValue();
        return var10000 != null ? var10000.hashCode() : 0;
    }

    public boolean equals(@Nullable Object var1) {
        if (this != var1) {
            if (var1 instanceof Uint256) {
                Uint256 var2 = (Uint256)var1;
                if (Intrinsics.areEqual(this.getValue(), var2.getValue())) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }

    @JvmStatic
    @NotNull
    public static final Uint256 fromHex(@NotNull String value) {
        return Companion.fromHex(value);
    }

    @Metadata(
            mv = {2, 0, 0},
            k = 1,
            xi = 2,
            d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0007R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"},
            d2 = {"Lcom/swmansion/starknet/data/types/Uint256$Companion;", "", "()V", "MAX", "Ljava/math/BigInteger;", "ONE", "Lcom/swmansion/starknet/data/types/Uint256;", "ZERO", "fromHex", "value", "", "starknet-jvm-learn"}
    )
    public static final class Companion {
        @JvmStatic
        @NotNull
        public final Uint256 fromHex(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new Uint256(ParseHex.parseHex(value));
        }

        private Companion() {
        }

        // $FF: synthetic method
        public Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}


