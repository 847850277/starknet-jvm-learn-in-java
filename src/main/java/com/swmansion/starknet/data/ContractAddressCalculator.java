package com.swmansion.starknet.data;

import com.swmansion.starknet.crypto.StarknetCurve;
import com.swmansion.starknet.data.types.Felt;
import org.bouncycastle.jcajce.provider.digest.Keccak;
import java.math.BigInteger;
import java.util.List;
import java.util.Locale;

public class ContractAddressCalculator {
    private static final Felt CONTRACT_ADDRESS_PREFIX = Felt.Companion.fromHex("0x535441524b4e45545f434f4e54524143545f41444452455353");

    public static Felt calculateAddressFromHash(Felt classHash, List<Felt> calldata, Felt salt, Felt deployerAddress) {
        return StarknetCurve.pedersenOnElements(
                CONTRACT_ADDRESS_PREFIX,
                deployerAddress,
                salt,
                classHash,
                StarknetCurve.pedersenOnElements(calldata)
        );
    }

    public static Felt calculateAddressFromHash(Felt classHash, List<Felt>  calldata, Felt salt) {
        return calculateAddressFromHash(classHash, calldata, salt, Felt.ZERO);
    }

    public static boolean isChecksumAddressValid(String address) {
        return calculateChecksumAddress(Felt.Companion.fromHex(address)).equals(address);
    }

    public static String calculateChecksumAddress(Felt address) {
        String stringAddress = address.getValue().toString(16).toLowerCase(Locale.ENGLISH);
        stringAddress = String.format("%64s", stringAddress).replace(' ', '0');
        char[] chars = stringAddress.toCharArray();
        byte[] hashed = new Keccak.Digest256().digest(new BigInteger(stringAddress, 16).toByteArray());
        for (int i = 0; i < chars.length; i++) {
            if (Character.isLetter(chars[i]) && (hashed[hashed.length - 1 - i / 2] & (0x0F << ((i % 2) * 4))) >= 0x08) {
                chars[i] = Character.toUpperCase(chars[i]);
            }
        }
        return "0x" + new String(chars);
    }
}
