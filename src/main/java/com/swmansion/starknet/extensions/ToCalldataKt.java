package com.swmansion.starknet.extensions;

import com.swmansion.starknet.data.types.Felt;
import com.swmansion.starknet.data.types.conversions.ConvertibleToCalldata;
import kotlin.Metadata;

import java.util.ArrayList;
import java.util.List;

@Metadata(
        mv = {2, 0, 0},
        k = 2,
        xi = 2,
        d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0001¨\u0006\u0004"},
        d2 = {"toCalldata", "", "Lcom/swmansion/starknet/data/types/Felt;", "Lcom/swmansion/starknet/data/types/conversions/ConvertibleToCalldata;", "starknet-jvm-learn"}
)
public class ToCalldataKt {

    public static List<Felt> toCalldata(List<ConvertibleToCalldata> list) {
        List<Felt> result = new ArrayList<>();
        for (ConvertibleToCalldata item : list) {
            result.addAll(item.toCalldata());
        }
        return result;
    }

}
