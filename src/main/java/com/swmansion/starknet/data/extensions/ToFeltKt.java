package com.swmansion.starknet.data.extensions;

import com.swmansion.starknet.data.types.Felt;
import com.swmansion.starknet.data.types.NumAsHexBase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

import java.math.BigInteger;

@Metadata(
        mv = {2, 0, 0},
        k = 2,
        xi = 2,
        d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0006\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\b\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\n\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\f¨\u0006\r"},
        d2 = {"toFelt", "Lcom/swmansion/starknet/data/types/Felt;", "Lcom/swmansion/starknet/data/types/NumAsHexBase;", "getToFelt", "(Lcom/swmansion/starknet/data/types/NumAsHexBase;)Lcom/swmansion/starknet/data/types/Felt;", "Ljava/math/BigInteger;", "(Ljava/math/BigInteger;)Lcom/swmansion/starknet/data/types/Felt;", "", "(I)Lcom/swmansion/starknet/data/types/Felt;", "", "(J)Lcom/swmansion/starknet/data/types/Felt;", "", "(Ljava/lang/String;)Lcom/swmansion/starknet/data/types/Felt;", "starknet-jvm-learn"}
)
public class ToFeltKt {

    public static final Felt getToFelt(BigInteger $this$toFelt) {
        Intrinsics.checkNotNullParameter($this$toFelt, "$this$toFelt");
        return new Felt($this$toFelt);
    }

    // $FF: synthetic method
    public static final Felt getToFelt(String $this$toFelt) {
        Intrinsics.checkNotNullParameter($this$toFelt, "$this$toFelt");
        return Felt.Companion.fromHex($this$toFelt);
    }

    // $FF: synthetic method
    public static final Felt getToFelt(int $this$toFelt) {
        return new Felt($this$toFelt);
    }

    // $FF: synthetic method
    public static final Felt getToFelt(long $this$toFelt) {
        return new Felt($this$toFelt);
    }

    // $FF: synthetic method
    public static final Felt getToFelt(NumAsHexBase $this$toFelt) {
        Intrinsics.checkNotNullParameter($this$toFelt, "$this$toFelt");
        return new Felt($this$toFelt.getValue());
    }
}
