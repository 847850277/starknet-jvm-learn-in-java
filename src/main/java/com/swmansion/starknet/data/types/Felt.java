package com.swmansion.starknet.data.types;

import com.swmansion.starknet.data.ParseHex;
import com.swmansion.starknet.extensions.ToHexKt;
import com.swmansion.starknet.data.types.conversions.ConvertibleToCalldata;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigInteger;
import java.util.List;

@Metadata(
        mv = {2, 0, 0},
        k = 1,
        xi = 2,
        d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u001a2\u00020\u00012\u00020\u0002:\u0001\u001aB\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\r\u0012\u0006\u0010\u0003\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\f\u001a\u00020\bHÆ\u0003J\u0013\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\bHÆ\u0001J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001J\b\u0010\u0015\u001a\u00020\u000fH\u0016J\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00000\u0017H\u0016J\u0006\u0010\u0018\u001a\u00020\u000fJ\b\u0010\u0019\u001a\u00020\u000fH\u0016R\u0014\u0010\u0003\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u001b"},
        d2 = {"Lcom/swmansion/starknet/data/types/Felt;", "Lcom/swmansion/starknet/data/types/NumAsHexBase;", "Lcom/swmansion/starknet/data/types/conversions/ConvertibleToCalldata;", "value", "", "(J)V", "", "(I)V", "Ljava/math/BigInteger;", "(Ljava/math/BigInteger;)V", "getValue", "()Ljava/math/BigInteger;", "component1", "copy", "decString", "", "equals", "", "other", "", "hashCode", "hexString", "toCalldata", "", "toShortString", "toString", "Companion", "starknet-jvm-learn"}
)
public final class Felt extends NumAsHexBase implements ConvertibleToCalldata {


    @NotNull
    private final BigInteger value;
    @JvmField
    @NotNull
    public static final BigInteger PRIME = new BigInteger("800000000000011000000000000000000000000000000000000000000000001", 16);
    @JvmField
    @NotNull
    public static final BigInteger MAX;
    @JvmField
    @NotNull
    public static final Felt ZERO;
    @JvmField
    @NotNull
    public static final Felt ONE;
    @NotNull
    public static final Companion Companion = new Companion((DefaultConstructorMarker)null);

    @NotNull
    @Override
    public List toCalldata() {
        return CollectionsKt.listOf(this);
    }

    @NotNull
    public String toString() {
        return "Felt(" + ToHexKt.toHex(this.getValue()) + ')';
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
    public final String toShortString() {
        String hexString = this.getValue().toString(16);
        if (hexString.length() % 2 == 1) {
            Intrinsics.checkNotNullExpressionValue(hexString, "hexString");
            hexString = StringsKt.padStart(hexString, hexString.length() + 1, '0');
        }

        Intrinsics.checkNotNullExpressionValue(hexString, "hexString");

        StringBuilder decoded = new StringBuilder();
        for (int i = 0; i < hexString.length(); i += 2) {
            String hex = hexString.substring(i, i + 2);
            int intValue = Integer.parseInt(hex, 16);
            decoded.append((char) intValue);
        }
        return decoded.toString();
    }

    @NotNull
    public BigInteger getValue() {
        return this.value;
    }

    public Felt(@NotNull BigInteger value) {
        super(value);
        Intrinsics.checkNotNullParameter(value, "value");
        this.value = value;
        if (this.getValue().compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Default Felt constructor does not accept negative numbers, [" + this.getValue() + "] given.");
        } else if (this.getValue().compareTo(PRIME) >= 0) {
            throw new IllegalArgumentException("Default Felt constructor accepts values smaller than Felt.PRIME, [" + this.getValue() + "] given.");
        }
    }

    public Felt(long value) {
        this(BigInteger.valueOf(value));
    }
//
    public Felt(int value) {
        this(BigInteger.valueOf((long)value));
    }

    static {
        BigInteger var0 = PRIME;
        BigInteger var10000 = BigInteger.ONE;
        Intrinsics.checkNotNullExpressionValue(var10000, "BigInteger.ONE");
        BigInteger var1 = var10000;
        var10000 = var0.subtract(var1);
        Intrinsics.checkNotNullExpressionValue(var10000, "subtract(...)");
        MAX = var10000;
        BigInteger var10002 = BigInteger.ZERO;
        Intrinsics.checkNotNullExpressionValue(var10002, "BigInteger.ZERO");
        ZERO = new Felt(var10002);
        var10002 = BigInteger.ONE;
        Intrinsics.checkNotNullExpressionValue(var10002, "BigInteger.ONE");
        ONE = new Felt(var10002);
    }

    @NotNull
    public final BigInteger component1() {
        return this.getValue();
    }

    @NotNull
    public final Felt copy(@NotNull BigInteger value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return new Felt(value);
    }

    // $FF: synthetic method
    public static Felt copy$default(Felt var0, BigInteger var1, int var2, Object var3) {
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
            if (var1 instanceof Felt) {
                Felt var2 = (Felt)var1;
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
    public static final Felt fromHex(@NotNull String value) {
        return Companion.fromHex(value);
    }

    @JvmStatic
    @NotNull
    public static final Felt fromShortString(@NotNull String value) {
        return Companion.fromShortString(value);
    }

    @Metadata(
            mv = {2, 0, 0},
            k = 1,
            xi = 2,
            d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0002J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011HÆ\u0001R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"},
            d2 = {"Lcom/swmansion/starknet/data/types/Felt$Companion;", "", "()V", "MAX", "Ljava/math/BigInteger;", "ONE", "Lcom/swmansion/starknet/data/types/Felt;", "PRIME", "ZERO", "fromHex", "value", "", "fromShortString", "isAscii", "", "string", "serializer", "Lkotlinx/serialization/KSerializer;", "starknet-jvm-learn"}
    )
    public static final class Companion {
        @JvmStatic
        @NotNull
        public final Felt fromHex(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new Felt(ParseHex.parseHex(value));
        }

        @JvmStatic
        @NotNull
        public final Felt fromShortString(@NotNull String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            if (value.length() > 31) {
                    throw (new IllegalArgumentException("Short string cannot be longer than 31 characters."));
            } else if (!((Companion)this).isAscii(value)) {
                    throw (new IllegalArgumentException("String to be encoded must be an ascii string."));
            } else {
                char[] chars = value.toCharArray();
                StringBuilder encoded = new StringBuilder();
                for (char c : chars) {
                    String hex = Integer.toHexString(c);
                    hex = String.format("%2s", hex).replace(' ', '0');
                    encoded.append(hex);
                }
                String encodedString = encoded.toString();
                return ((Companion)this).fromHex("0x" + encodedString);
            }
        }

        private final boolean isAscii(String string) {
            String var4 = string;
            int var5 = string.length();

            for(int var3 = 0; var3 < var5; ++var3) {
                char var2 = var4.charAt(var3);
                if (var2 < 0 || var2 > 127) {
                    return false;
                }
            }

            return true;
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
            //return (KSerializer)FeltSerializer.INSTANCE;
        }
    }


}
