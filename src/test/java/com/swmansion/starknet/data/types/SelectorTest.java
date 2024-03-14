package com.swmansion.starknet.data.types;

import com.swmansion.starknet.data.Selector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SelectorTest {

    @Test
    public final void selectorFromName() {
        Felt test = Selector.selectorFromName("test");
        System.out.println(test);
        Assertions.assertEquals(Selector.selectorFromName("test"), Felt.Companion.fromHex("0x22ff5f21f0b81b113e63f7db6da94fedef11b2119b4088b89664fb9a3cb658"));
        Assertions.assertEquals(Selector.selectorFromName("initialize"), Felt.Companion.fromHex("0x79dc0da7c54b95f10aa182ad0a46400db63156920adb65eca2654c0945a463"));
        Assertions.assertEquals(Selector.selectorFromName("mint"), Felt.Companion.fromHex("0x2f0b3c5710379609eb5495f1ecd348cb28167711b73609fe565a72734550354"));
        Assertions.assertEquals(Selector.selectorFromName("__default__"), Felt.Companion.fromHex("0x0"));
        Assertions.assertEquals(Selector.selectorFromName("__l1_default__"), Felt.Companion.fromHex("0x0"));
    }
}
