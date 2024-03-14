package com.swmansion.starknet.data.types;

import com.swmansion.starknet.data.Selector;
import com.swmansion.starknet.data.types.conversions.ConvertibleToCalldata;
import com.swmansion.starknet.extensions.ToCalldataKt;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Call {
    private  Felt contractAddress;
    private  Felt entrypoint;
    private  List<Felt> calldata;

    public Call(Felt contractAddress, Felt entrypoint, List<Felt> calldata) {
        this.contractAddress = contractAddress;
        this.entrypoint = entrypoint;
        this.calldata = calldata;
    }

    public Call(Felt contractAddress, String entrypoint, List<Felt> calldata) {
        this(contractAddress, Selector.selectorFromName(entrypoint), calldata);
    }

    public Call(Felt contractAddress, Felt entrypoint) {
        this(contractAddress, entrypoint, new ArrayList<>());
    }

    public Call(Felt contractAddress, String entrypoint) {
        this(contractAddress, Selector.selectorFromName(entrypoint), new ArrayList<>());
    }

    public Felt getContractAddress() {
        return contractAddress;
    }

    public Felt getEntrypoint() {
        return entrypoint;
    }

    public List<Felt> getCalldata() {
        return calldata;
    }

    public static Call fromCallArguments(Felt contractAddress, Felt entrypoint, List<ConvertibleToCalldata> arguments) {
        List<Felt>  calldata = ToCalldataKt.toCalldata(arguments);
        return new Call(contractAddress, entrypoint, calldata);
    }

    public static Call fromCallArguments(Felt contractAddress, String entrypoint, List<ConvertibleToCalldata> arguments) {
        return fromCallArguments(contractAddress, Selector.selectorFromName(entrypoint), arguments);
    }

    public Call copy() {
        return new Call(this.contractAddress, this.entrypoint, new ArrayList<>(this.calldata));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Call call = (Call) o;
        return Objects.equals(contractAddress, call.contractAddress) && Objects.equals(entrypoint, call.entrypoint) && Objects.equals(calldata, call.calldata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractAddress, entrypoint, calldata);
    }
}
