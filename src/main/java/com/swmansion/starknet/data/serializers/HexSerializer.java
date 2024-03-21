package com.swmansion.starknet.data.serializers;

import com.swmansion.starknet.data.types.NumAsHexBase;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;

import java.util.function.Function;

public abstract class HexSerializer<T extends NumAsHexBase> implements KSerializer<T> {
    private Function<String, T> fromHex;
    private Function<T, String> hexString;
    private SerialDescriptor descriptor;

    public HexSerializer(Function<String, T> fromHex, Function<T, String> hexString, SerialDescriptor descriptor) {
        this.fromHex = fromHex;
        this.hexString = hexString;
        this.descriptor = descriptor;
    }

    @Override
    public T deserialize(Decoder decoder)  {
        String hex = decoder.decodeString();
        return fromHex.apply(hex);
    }

    @Override
    public void serialize(Encoder encoder, T value) {
        encoder.encodeString(hexString.apply(value));
    }

    @Override
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }
}
