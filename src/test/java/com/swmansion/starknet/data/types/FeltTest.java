package com.swmansion.starknet.data.types;

import com.swmansion.starknet.data.types.conversions.ConvertibleToCalldata;
import com.swmansion.starknet.extensions.ToCalldataKt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FeltTest {

    @Test
    public void fromShortString_short_string(){
        Felt felt = Felt.fromShortString("hello");
        Assertions.assertEquals(Felt.fromHex("0x68656c6c6f"), felt);
    }

    @Test
    public void toShortString_short_string(){
        String decoded = Felt.Companion.fromHex("0xa68656c6c6f").toShortString();
        Assertions.assertEquals("\nhello", decoded);

        String shortString = Felt.fromHex("0x68656c6c6f").toShortString();
        Assertions.assertEquals("hello", shortString);
    }

    @Test
    public void fromShortString_non_ascii_string_should_fail(){
        assertThrows(IllegalArgumentException.class, () -> Felt.fromShortString("hello\uD83D\uDE00"));
    }

    @Test
    public void felt_array_is_convertible_to_calldata(){
        ArrayList<ConvertibleToCalldata> convertibleToCalldata = new ArrayList<>();

        FeltArray feltArray1 = new FeltArray(new Felt(BigInteger.valueOf(100)), new Felt(BigInteger.valueOf(200)));
        FeltArray feltArray2 = new FeltArray(Arrays.asList(new Felt(BigInteger.valueOf(300)), new Felt(BigInteger.valueOf(400))));
        Felt felt = new Felt(BigInteger.valueOf(500));
        feltArray2.add(felt);
        FeltArray emptyFeltArray = new FeltArray();

        convertibleToCalldata.add(new Felt(BigInteger.valueOf(15)));
        convertibleToCalldata.add(new Felt(BigInteger.valueOf(feltArray1.size())));
        convertibleToCalldata.addAll(feltArray1);
        convertibleToCalldata.add(new Felt(BigInteger.valueOf(feltArray2.size())));
        convertibleToCalldata.addAll(feltArray2);
        convertibleToCalldata.add(new Felt(BigInteger.valueOf(emptyFeltArray.size())));
        convertibleToCalldata.addAll(emptyFeltArray);

        List<Felt> calldata = ToCalldataKt.toCalldata(convertibleToCalldata);

        List<Felt> expectedCalldata = Arrays.asList(
                new Felt(BigInteger.valueOf(15)),
                new Felt(BigInteger.valueOf(2)), new Felt(BigInteger.valueOf(100)), new Felt(BigInteger.valueOf(200)),
                new Felt(BigInteger.valueOf(3)), new Felt(BigInteger.valueOf(300)), new Felt(BigInteger.valueOf(400)), new Felt(BigInteger.valueOf(500)),
                new Felt(BigInteger.ZERO)
        );
        Assertions.assertEquals(expectedCalldata, calldata);

    }

}
