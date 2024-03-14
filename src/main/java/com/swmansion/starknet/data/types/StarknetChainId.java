package com.swmansion.starknet.data.types;

public class StarknetChainId {

    private final Felt value;

    public static final StarknetChainId MAIN = new StarknetChainId(Felt.fromHex("0x534e5f4d41494e"));
    public static final StarknetChainId GOERLI = new StarknetChainId(Felt.fromHex("0x534e5f474f45524c49"));
    public static final StarknetChainId SEPOLIA = new StarknetChainId(Felt.fromHex("0x534e5f5345504f4c4941"));
    public static final StarknetChainId INTEGRATION_SEPOLIA = new StarknetChainId(Felt.fromHex("0x534e5f494e544547524154494f4e5f5345504f4c4941"));

    public StarknetChainId(Felt value) {
        this.value = value;
    }

    public Felt getValue() {
        return value;
    }

    public static StarknetChainId fromNetworkName(String networkName) {
        return new StarknetChainId(Felt.fromShortString(networkName));
    }

    public static StarknetChainId fromHex(String hex) {
        return new StarknetChainId(Felt.fromHex(hex));
    }

    public String toNetworkName() {
        return value.toShortString();
    }
}
