package com.swmansion.starknet.data;

import com.swmansion.starknet.crypto.Keccak;
import com.swmansion.starknet.data.types.Felt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.Charset;

@Metadata(
        mv = {2, 0, 0},
        k = 2,
        xi = 2,
        d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"},
        d2 = {"DEFAULT_ENTRY_POINT_NAME", "", "DEFAULT_ENTRY_POINT_SELECTOR", "", "DEFAULT_L1_ENTRY_POINT_NAME", "EXECUTE_ENTRY_POINT_NAME", "selectorFromName", "Lcom/swmansion/starknet/data/types/Felt;", "name", "starknet-jvm-learn"}
)

public class Selector {

    @NotNull
    public static final String DEFAULT_ENTRY_POINT_NAME = "__default__";
    @NotNull
    public static final String DEFAULT_L1_ENTRY_POINT_NAME = "__l1_default__";
    public static final int DEFAULT_ENTRY_POINT_SELECTOR = 0;
    @NotNull
    public static final String EXECUTE_ENTRY_POINT_NAME = "__execute__";

    @NotNull
    public static final Felt selectorFromName(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        if (!Intrinsics.areEqual(name, "__default__") && !Intrinsics.areEqual(name, "__l1_default__")) {
            Charset var2 = Charsets.US_ASCII;
            byte[] var10000 = name.getBytes(var2);
            Intrinsics.checkNotNullExpressionValue(var10000, "getBytes(...)");
            return Keccak.starknetKeccak(var10000);
        } else {
            return new Felt(0);
        }
    }
}
