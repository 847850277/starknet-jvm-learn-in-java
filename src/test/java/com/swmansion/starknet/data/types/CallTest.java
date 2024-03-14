package com.swmansion.starknet.data.types;

import cn.hutool.core.bean.BeanUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.swmansion.starknet.data.Selector.selectorFromName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallTest {

    private final Felt address = Felt.fromHex("0x111");
    private final String selectorString = "abcdef";
    private final Felt selector = selectorFromName(selectorString);
    private final Uint256 param = Uint256.fromHex("0x210439592369239639725932969795495939596");
    private final Felt low = param.getLow();
    private final Felt high = param.getHigh();
    private final List<Felt> calldata = Arrays.asList(low, high);

    private final Call expectedCall = new Call(address, selector, calldata);


    @Test
    public void createCallFromPrimaryConstructor() {
        Call call = new Call(address, selector, Arrays.asList(low, high));
        assertEquals(expectedCall, call);
    }

    @Test
    public void createCallFromStringEntrypoint() {
        Call call = new Call(address, selectorString, Arrays.asList(low, high));

        assertEquals(expectedCall, call);
    }

    @Test
    public void createCallWithoutCalldata() {
        Call call = new Call(address, selector);
        //TODO: This is a bug, the calldata should be an empty list
        Call copy = BeanUtil.copyProperties(expectedCall, Call.class);
        assertEquals(copy, call);
    }

    @Test
    public void createCallWithoutCalldataStringEntrypoint() {
        Call call = new Call(address, selectorString);
        //TODO This is a bug, the calldata should be an empty list
        Call copy = BeanUtil.copyProperties(expectedCall, Call.class);
        assertEquals(copy, call);
    }

    @Test
    public void createCallFromCallArguments() {
        Call call = Call.fromCallArguments(address, selector, Arrays.asList(param));
        assertEquals(expectedCall, call);
    }

    @Test
    public void createCallFromCallArgumentsStringEntrypoint() {
        Call call = Call.fromCallArguments(address, selectorString, Arrays.asList(param));

        assertEquals(expectedCall, call);
    }
}
