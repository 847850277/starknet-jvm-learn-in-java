package com.swmansion.starknet.crypto;

import com.swmansion.starknet.data.types.Felt;

import java.util.Arrays;
import java.util.List;

public class StarknetCurveSignature {
    private final Felt r;
    private final Felt s;

    public StarknetCurveSignature(Felt r, Felt s) {
        this.r = r;
        this.s = s;
    }

    public Felt getR() {
        return r;
    }

    public Felt getS() {
        return s;
    }

    public List<Felt> toList() {
        return Arrays.asList(r, s);
    }
}
