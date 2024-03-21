package com.swmansion.starknet.data.serializers;

import com.swmansion.starknet.data.types.Felt;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.internal.PrimitiveSerialDescriptor;
import org.jetbrains.annotations.NotNull;

public class FeltSerializer extends HexSerializer<Felt> {

//    public static void main(String[] args) {
//        PrimitiveKind.STRING string = PrimitiveKind.STRING.INSTANCE;
//        new PrimitiveSerialDescriptor("Felt", string);
//    }

    private static final FeltSerializer INSTANCE = new FeltSerializer();

    public FeltSerializer() {
        super(Felt::fromHex, Felt::hexString, new PrimitiveSerialDescriptor("Felt", PrimitiveKind.STRING.INSTANCE));
    }

    public static FeltSerializer getInstance() {
        return INSTANCE;
    }

    @Override
    public Felt patch(@NotNull Decoder decoder, Felt felt) {
        return null;
    }
}
