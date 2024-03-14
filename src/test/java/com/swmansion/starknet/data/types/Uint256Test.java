package com.swmansion.starknet.data.types;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class Uint256Test {

    @Test
    public void create_from_two_felts() {
        Felt felt1 = Felt.fromHex("0x123abc");
        Felt felt2 = Felt.fromHex("0x456");
        Uint256 uint2561 = new Uint256(felt1, felt2);
        Uint256 uint2562 = new Uint256(new BigInteger("377713427282241694444345814249262715910844"));
        Assertions.assertEquals(uint2561, uint2562);
    }

    @Test
    public void get_low_and_high(){
        Uint256 uint2562 = new Uint256(new BigInteger("377713427282241694444345814249262715910844"));
        Assertions.assertEquals(uint2562.getLow(), Felt.fromHex("0x123abc"));
        Assertions.assertEquals(uint2562.getHigh(), Felt.fromHex("0x456"));
    }
}
