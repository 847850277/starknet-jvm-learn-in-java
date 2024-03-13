package com.swmansion.starknet.data.types;

import java.math.BigInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
        mv = {2, 0, 0},
        k = 1,
        xi = 2,
        d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0000H\u0096\u0002J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\u000bH&R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0005\r\u000e\u000f\u0010\u0011¨\u0006\u0012"},
        d2 = {"Lcom/swmansion/starknet/data/types/NumAsHexBase;", "", "value", "Ljava/math/BigInteger;", "(Ljava/math/BigInteger;)V", "getValue", "()Ljava/math/BigInteger;", "compareTo", "", "other", "decString", "", "hexString", "Lcom/swmansion/starknet/data/types/Felt;", "Lcom/swmansion/starknet/data/types/NumAsHex;", "Lcom/swmansion/starknet/data/types/Uint128;", "Lcom/swmansion/starknet/data/types/Uint256;", "Lcom/swmansion/starknet/data/types/Uint64;", "starknet-jvm-learn"}
)
public abstract class NumAsHexBase implements Comparable{

    @NotNull
    private final BigInteger value;

    public int compareTo(@NotNull NumAsHexBase other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return this.getValue().compareTo(other.getValue());
    }

    // $FF: synthetic method
    // $FF: bridge method
    public int compareTo(Object var1) {
        return this.compareTo((NumAsHexBase)var1);
    }

    @NotNull
    public abstract String hexString();

    @NotNull
    public abstract String decString();

    @NotNull
    public BigInteger getValue() {
        return this.value;
    }

    public NumAsHexBase(BigInteger value) {
        this.value = value;
    }

    // $FF: synthetic method
    public NumAsHexBase(BigInteger value, DefaultConstructorMarker $constructor_marker) {
        this(value);
    }
}
