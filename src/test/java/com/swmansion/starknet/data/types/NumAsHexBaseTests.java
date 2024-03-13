package com.swmansion.starknet.data.types;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumAsHexBaseTests {

   @Test
   public void numbers_can_t_be_created_with_a_negative_value(){
       BigInteger bigInteger = BigInteger.valueOf(-1);
       assertThrows(IllegalArgumentException.class, () -> new Felt(bigInteger));
       assertThrows(IllegalArgumentException.class, () -> new Uint64(bigInteger));
       assertThrows(IllegalArgumentException.class, () -> new Uint128(bigInteger));
       assertThrows(IllegalArgumentException.class, () -> new Uint256(bigInteger));
   }

   @Test
   public void numbers_overflow(){
       assertThrows(IllegalArgumentException.class, () -> new Felt(Felt.PRIME));
       BigInteger max1 = Uint64.MAX.add(BigInteger.ONE);
       BigInteger max2 = Uint128.MAX.add(BigInteger.ONE);
       BigInteger max3 = Uint256.MAX.add(BigInteger.ONE);
       assertThrows(IllegalArgumentException.class, () -> new Uint64(max1));
       assertThrows(IllegalArgumentException.class, () -> new Uint128(max2));
       assertThrows(IllegalArgumentException.class, () -> new Uint256(max3));
   }

   @Test
   public void numbers_to_string(){
        assertEquals("Felt(0xabcdef01234567890)",Felt.fromHex("0xabcdef01234567890").toString());
        assertEquals("NumAsHex(0xabcdef01234567890)",NumAsHex.fromHex("0xabcdef01234567890").toString());
        assertEquals("Uint128(0xabcdef01234567890)",Uint128.fromHex("0xabcdef01234567890").toString());
        assertEquals("Uint256(58462017464642449753835857636044240746640)",Uint256.fromHex("0xabcdef01234567890abcdef01234567890").toString());
   }

}
