package com.swmansion.starknet.data;

import com.swmansion.starknet.crypto.StarknetCurve;
import com.swmansion.starknet.data.types.Felt;
import kotlinx.serialization.json.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TypedData {

    private Map<String, List<TypeBase>> types;
    private String primaryType;
    private JsonObject domain;
    private JsonObject message;

    private TypedData(Map<String, List<TypeBase>> types, String primaryType, JsonObject domain, JsonObject message) {
        this.types = types;
        this.primaryType = primaryType;
        this.domain = domain;
        this.message = message;
    }

    public TypedData(Map<String, List<TypeBase>> types, String primaryType, String domain, String message) {
        //this(types, primaryType, Json.parseToJsonElement(domain).getAsJsonObject(), Json.parseToJsonElement(message).getAsJsonObject());
    }

    public Felt getMessageHash(Felt accountAddress) {
        return StarknetCurve.pedersenOnElements(
                Felt.fromShortString("StarkNet Message"),
                getStructHash("StarkNetDomain", domain),
                accountAddress,
                getStructHash(primaryType, message)
        );
    }

    private Felt getStructHash(String typeName, JsonObject data) {
        List<Felt> encodedData = encodeData(typeName, data);

        //return StarknetCurve.pedersenOnElements(getTypeHash(typeName), encodedData.toArray(new Felt[0]));
        return null;
    }

    private List<Felt> encodeData(String typeName, JsonObject data) {
        List<Felt> values = new ArrayList<>();

//        for (TypeBase param : types.get(typeName)) {
//            Pair<String, Felt> encodedValue = encodeValue(
//                    param.getType(),
//                    data.get(param.getName()),
//                    new Context(typeName, param.getName())
//            );
//            values.add(encodedValue.getSecond());
//        }

        return values;
    }



    // Implement the remaining methods as per your requirements
}