package com.swmansion.starknet.data.types.conversions;

import com.swmansion.starknet.data.types.Felt;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Metadata(
        mv = {2, 0, 0},
        k = 1,
        xi = 2,
        d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&¨\u0006\u0005"},
        d2 = {"Lcom/swmansion/starknet/data/types/conversions/ConvertibleToCalldata;", "", "toCalldata", "", "Lcom/swmansion/starknet/data/types/Felt;", "starknet-jvm-learn"}
)
public interface ConvertibleToCalldata {

    @NotNull
    List<Felt> toCalldata();

}
