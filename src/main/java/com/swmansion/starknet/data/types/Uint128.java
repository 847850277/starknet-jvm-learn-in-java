package com.swmansion.starknet.data.types;

import com.swmansion.starknet.data.ParseHex;
import com.swmansion.starknet.data.extensions.ToFeltKt;
import com.swmansion.starknet.data.extensions.ToHexKt;
import com.swmansion.starknet.data.types.conversions.ConvertibleToCalldata;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigInteger;
import java.util.List;

@Metadata(
        mv = {2, 0, 0},
        k = 1,
        xi = 2,
        d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 \u001a2\u00020\u00012\u00020\u0002:\u0001\u001aB\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\r\u0012\u0006\u0010\u0003\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\f\u001a\u00020\bHÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\bHÆ\u0001J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001J\b\u0010\u0015\u001a\u00020\u000fH\u0016J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0016J\b\u0010\u0019\u001a\u00020\u000fH\u0016R\u0014\u0010\u0003\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u001b"},
        d2 = {"Lcom/swmansion/starknet/data/types/Uint128;", "Lcom/swmansion/starknet/data/types/NumAsHexBase;", "Lcom/swmansion/starknet/data/types/conversions/ConvertibleToCalldata;", "value", "", "(J)V", "", "(I)V", "Ljava/math/BigInteger;", "(Ljava/math/BigInteger;)V", "getValue", "()Ljava/math/BigInteger;", "component1", "copy", "decString", "", "equals", "", "other", "", "hashCode", "hexString", "toCalldata", "", "Lcom/swmansion/starknet/data/types/Felt;", "toString", "Companion", "starknet-jvm-learn"}
)
public class Uint128 extends NumAsHexBase implements ConvertibleToCalldata {

    @NotNull
    private final BigInteger value;
    @JvmField
    @NotNull
    public static final BigInteger MAX;
    @JvmField
    @NotNull
    public static final Uint128 ZERO;
    @JvmField
    @NotNull
    public static final Uint128 ONE;
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker)null);

    @NotNull
    public List toCalldata() {
        return CollectionsKt.listOf(ToFeltKt.getToFelt(this.getValue()));
    }

    @NotNull
    public String toString() {
        return "Uint128(" + ToHexKt.toHex(this.getValue()) + ')';
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

    public Uint128(@NotNull BigInteger value) {
        super(value, (DefaultConstructorMarker)null);
        Intrinsics.checkNotNullParameter(value, "value");
        this.value = value;
        if (this.getValue().compareTo(BigInteger.ZERO) < 0) {
            try {
                throw (Throwable)(new IllegalArgumentException("Default Uint128 constructor does not accept negative numbers, [" + this.getValue() + "] given."));
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        } else if (this.getValue().compareTo(MAX) >= 0) {
            try {
                throw (Throwable)(new IllegalArgumentException("Default Uint128 constructor does not accept numbers higher than 2^128-1, [" + this.getValue() + "] given."));
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

//    public Uint128(long value) {
//        BigInteger var10001 = BigInteger.valueOf(value);
//        Intrinsics.checkNotNullExpressionValue(var10001, "BigInteger.valueOf(value)");
//        this(var10001);
//    }
//
//    public Uint128(int value) {
//        BigInteger var10001 = BigInteger.valueOf((long)value);
//        Intrinsics.checkNotNullExpressionValue(var10001, "BigInteger.valueOf(value.toLong())");
//        this(var10001);
//    }

    static {
        BigInteger var10000 = BigInteger.valueOf(2L).pow(128);
        Intrinsics.checkNotNullExpressionValue(var10000, "BigInteger.valueOf(2).pow(128)");
        BigInteger var0 = var10000;
        var10000 = BigInteger.ONE;
        Intrinsics.checkNotNullExpressionValue(var10000, "BigInteger.ONE");
        BigInteger var1 = var10000;
        var10000 = var0.subtract(var1);
        Intrinsics.checkNotNullExpressionValue(var10000, "subtract(...)");
        MAX = var10000;
        BigInteger var10002 = BigInteger.ZERO;
        Intrinsics.checkNotNullExpressionValue(var10002, "BigInteger.ZERO");
        ZERO = new Uint128(var10002);
        var10002 = BigInteger.ONE;
        Intrinsics.checkNotNullExpressionValue(var10002, "BigInteger.ONE");
        ONE = new Uint128(var10002);
    }

    @NotNull
    public final BigInteger component1() {
        return this.getValue();
    }

    @NotNull
    public final Uint128 copy(@NotNull BigInteger value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return new Uint128(value);
    }

    // $FF: synthetic method
    public static Uint128 copy$default(Uint128 var0, BigInteger var1, int var2, Object var3) {
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
            if (var1 instanceof Uint128) {
                Uint128 var2 = (Uint128)var1;
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
    public static final Uint128 fromHex(@NotNull String value) {
        return Companion.fromHex(value);
    }

    @Metadata(
            mv = {2, 0, 0},
            k = 1,
            xi = 2,
            d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0007J\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\fHÆ\u0001R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\r"},
            d2 = {"Lcom/swmansion/starknet/data/types/Uint128$Companion;", "", "()V", "MAX", "Ljava/math/BigInteger;", "ONE", "Lcom/swmansion/starknet/data/types/Uint128;", "ZERO", "fromHex", "value", "", "serializer", "Lkotlinx/serialization/KSerializer;", "starknet-jvm-learn"}
    )
    public static final class Companion {
        @JvmStatic
        @NotNull
        public final Uint128 fromHex(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new Uint128(ParseHex.parseHex(value));
        }

        private Companion() {
        }

        // $FF: synthetic method
        public Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final KSerializer serializer() {
            return null;
            //return (KSerializer)Uint128Serializer.INSTANCE;
        }
    }


}
