package com.swmansion.starknet.crypto;

import com.swmansion.starknet.data.types.Felt;
import com.swmansion.starknet.extensions.StarknetCurveKt;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.signers.HMacDSAKCalculator;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

public class StarknetCurve {

    public static final BigInteger CURVE_ORDER = new BigInteger("800000000000010FFFFFFFFFFFFFFFFB781126DCAE7B2321E66A241ADC64D2F", 16);

    static {
        System.loadLibrary("crypto_jni");
    }

    private static native byte[] pedersen(byte[] first, byte[] second);

    public static Felt pedersen(Felt first, Felt second) {
        byte[] hash = pedersen(StarknetCurveKt.feltToNative(first), StarknetCurveKt.feltToNative(second));
        StarknetCurveKt.reverse(hash);
        return new Felt(new BigInteger(hash));
    }

    public static Felt pedersen(Iterable<Felt> values) {
        Felt result = Felt.ZERO;
        for (Felt value : values) {
            result = pedersen(result, value);
        }
        return result;
    }

    public static Felt pedersenOnElements(Collection<Felt> values) {
        return pedersen(pedersen(values), new Felt(values.size()));
    }

    public static Felt pedersenOnElements(Felt... values) {
        return pedersenOnElements(Arrays.asList(values));
    }

    private static native byte[] sign(byte[] privateKey, byte[] hash, byte[] k);

    private static StarknetCurveSignature sign(Felt privateKey, Felt hash, BigInteger k) {
        byte[] signature = sign(StarknetCurveKt.feltToNative(privateKey), StarknetCurveKt.feltToNative(hash), StarknetCurveKt.bigintToNative(k));
        BigInteger r = new BigInteger(Arrays.copyOfRange(signature, 0, 32));
        StarknetCurveKt.reverse(r.toByteArray());
        BigInteger w = new BigInteger(Arrays.copyOfRange(signature, 32, 64));
        StarknetCurveKt.reverse(w.toByteArray());
        BigInteger s = w.modInverse(CURVE_ORDER);
        return new StarknetCurveSignature(new Felt(r), new Felt(s));
    }

    public static StarknetCurveSignature sign(Felt privateKey, Felt hash) {
        HMacDSAKCalculator cal = new HMacDSAKCalculator(new SHA256Digest());
        cal.init(CURVE_ORDER, privateKey.getValue(), hash.getValue().toByteArray());
        Exception lastError = null;
        for (int i = 0; i < 3; i++) {
            try {
                return sign(privateKey, hash, cal.nextK());
            } catch (Exception e) {
                lastError = e;
            }
        }
        if (lastError == null) {
            throw new AssertionError("No signature or error after signing.");
        }
        throw new RuntimeException("Failed to sign", lastError);
    }

    private static native boolean verify(byte[] publicKey, byte[] hash, byte[] r, byte[] w);

    public static boolean verify(Felt publicKey, Felt hash, Felt r, Felt s) {
        BigInteger w = s.getValue().modInverse(CURVE_ORDER);
        return verify(StarknetCurveKt.feltToNative(publicKey), StarknetCurveKt.feltToNative(hash), StarknetCurveKt.feltToNative(r), StarknetCurveKt.bigintToNative(w));
    }

    private static native byte[] getPublicKey(byte[] privateKey);

    public static Felt getPublicKey(Felt privateKey) {
        if (privateKey.equals(Felt.ZERO)) {
            throw new IllegalArgumentException("Private key is invalid");
        }
        byte[] publicKey = getPublicKey(StarknetCurveKt.feltToNative(privateKey));
        StarknetCurveKt.reverse(publicKey);
        return new Felt(new BigInteger(publicKey));
    }
}
