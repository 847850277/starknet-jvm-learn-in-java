package com.swmansion.starknet.data.types;


import com.swmansion.starknet.data.ParseHex;
//import com.swmansion.starknet.data.serializers.NumAsHexSerializer;
import java.math.BigInteger;

import com.swmansion.starknet.data.extensions.ToHexKt;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

//@Serializable(
//        with = NumAsHexSerializer.class
//)
@Metadata(
        mv = {2, 0, 0},
        k = 1,
        xi = 2,
        d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0087\b\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000b\u001a\u00020\u0007HÆ\u0003J\u0013\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0007HÆ\u0001J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001J\b\u0010\u0014\u001a\u00020\u000eH\u0016J\b\u0010\u0015\u001a\u00020\u000eH\u0016R\u0014\u0010\u0002\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0017"},
        d2 = {"Lcom/swmansion/starknet/data/types/NumAsHex;", "Lcom/swmansion/starknet/data/types/NumAsHexBase;", "value", "", "(J)V", "", "(I)V", "Ljava/math/BigInteger;", "(Ljava/math/BigInteger;)V", "getValue", "()Ljava/math/BigInteger;", "component1", "copy", "decString", "", "equals", "", "other", "", "hashCode", "hexString", "toString", "Companion", "starknet-jvm-learn"}
)
public final class NumAsHex extends NumAsHexBase{

    @NotNull
    private final BigInteger value;
    @JvmField
    @NotNull
    public static final NumAsHex ZERO;
    @JvmField
    @NotNull
    public static final NumAsHex ONE;
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker)null);

    public NumAsHex(@NotNull BigInteger var10002) {
        super(var10002);
        //super(var10002);
        this.value = var10002;
    }

    @NotNull
    public String toString() {
        return "NumAsHex(" + ToHexKt.toHex(this.getValue()) + ')';
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



    static {
        BigInteger var10002 = BigInteger.ZERO;
        Intrinsics.checkNotNullExpressionValue(var10002, "BigInteger.ZERO");
        ZERO = new NumAsHex(var10002);
        var10002 = BigInteger.ONE;
        Intrinsics.checkNotNullExpressionValue(var10002, "BigInteger.ONE");
        ONE = new NumAsHex(var10002);
    }



//    public NumAsHex(BigInteger value) {
//        //Intrinsics.checkNotNullParameter(value, "value");
//        this.value = value;
//        //super(value, (DefaultConstructorMarker)null);
//        if (this.getValue().compareTo(BigInteger.ZERO) < 0) {
//            try {
//                throw (Throwable)(new IllegalArgumentException("Default NumAsHex constructor does not accept negative numbers, [" + this.getValue() + "] given."));
//            } catch (Throwable e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

//    public NumAsHex(long value) {
//        BigInteger var10001 = BigInteger.valueOf(value);
//        Intrinsics.checkNotNullExpressionValue(var10001, "BigInteger.valueOf(value)");
//        this(BigInteger.valueOf(value));
//    }
//
//    public NumAsHex(int value) {
//        BigInteger var10001 = BigInteger.valueOf((long)value);
//        Intrinsics.checkNotNullExpressionValue(var10001, "BigInteger.valueOf(value.toLong())");
//        this(BigInteger.valueOf(var10001));
//    }



    @NotNull
    public final BigInteger component1() {
        return this.getValue();
    }

    @NotNull
    public final NumAsHex copy(@NotNull BigInteger value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return new NumAsHex(value);
    }

    // $FF: synthetic method
    public static NumAsHex copy$default(NumAsHex var0, BigInteger var1, int var2, Object var3) {
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
            if (var1 instanceof NumAsHex) {
                NumAsHex var2 = (NumAsHex)var1;
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
    public static final NumAsHex fromHex(@NotNull String value) {
        return Companion.fromHex(value);
    }

    @Metadata(
            mv = {2, 0, 0},
            k = 1,
            xi = 2,
            d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\nHÆ\u0001R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"},
            d2 = {"Lcom/swmansion/starknet/data/types/NumAsHex$Companion;", "", "()V", "ONE", "Lcom/swmansion/starknet/data/types/NumAsHex;", "ZERO", "fromHex", "value", "", "serializer", "Lkotlinx/serialization/KSerializer;", "starknet-jvm-learn"}
    )
    public static final class Companion {
        @JvmStatic
        @NotNull
        public final NumAsHex fromHex(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new NumAsHex(ParseHex.parseHex(value));
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
            //return (KSerializer)NumAsHexSerializer.INSTANCE;
        }
    }

}
