package com.swmansion.starknet.provider.rpc;

import kotlinx.serialization.KSerializer;
import kotlinx.serialization.json.Json;

import java.util.function.Function;

public class BuildJsonHttpDeserializerKt {

    public static final Function buildJsonHttpDeserializer(final KSerializer deserializationStrategy, final Json deserializationJson) {
        return new JsonHttpDeserializer(deserializationStrategy.getClass()).getDeserializer();
    }
}
