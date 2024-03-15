package com.swmansion.starknet.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.swmansion.starknet.crypto.StarknetCurve;
import com.swmansion.starknet.data.types.Felt;
import kotlin.Pair;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonObject;
import com.swmansion.starknet.data.types.Felt;
import kotlinx.serialization.json.JsonObject;
import com.swmansion.starknet.crypto.StarknetCurve;
import com.swmansion.starknet.data.types.Felt;
import com.swmansion.starknet.data.types.MerkleTree;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonPrimitive;

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

        return StarknetCurve.pedersenOnElements(getTypeHash(typeName), encodedData.toArray(new Felt[0]));
    }

    private List<Felt> encodeData(String typeName, JsonObject data) {
        List<Felt> values = new ArrayList<>();

        for (TypeBase param : types.get(typeName)) {
            Pair<String, Felt> encodedValue = encodeValue(
                    param.getType(),
                    data.get(param.getName()),
                    new Context(typeName, param.getName())
            );
            values.add(encodedValue.getSecond());
        }

        return values;
    }

    Pair<String, Felt> encodeValue(String typeName, JsonElement value, Context context) {
        if (types.containsKey(typeName)) {
            return new Pair<>(typeName, getStructHash(typeName, (JsonObject) value));
        }

        if (types.containsKey(stripPointer(typeName))) {
            JsonArray array = (JsonArray) value;
            List<Felt> hashes = new ArrayList<>();
            for (JsonElement struct : array) {
                hashes.add(getStructHash(stripPointer(typeName), (JsonObject) struct));
            }
            Felt hash = StarknetCurve.pedersenOnElements(hashes);
            return new Pair<>(typeName, hash);
        }

        switch (typeName) {
            case "felt*": {
                JsonArray array = (JsonArray) value;
                List<Felt> feltArray = new ArrayList<>();
                for (JsonElement element : array) {
                    feltArray.add(feltFromPrimitive(element.getAsJsonPrimitive()));
                }
                Felt hash = StarknetCurve.pedersenOnElements(feltArray);
                return new Pair<>(typeName, hash);
            }
            case "felt":
                return new Pair<>("felt", feltFromPrimitive(value.getAsJsonPrimitive()));
            case "string":
                return new Pair<>("string", feltFromPrimitive(value.getAsJsonPrimitive()));
            case "raw":
                return new Pair<>("raw", feltFromPrimitive(value.getAsJsonPrimitive()));
            case "selector":
                return new Pair<>("felt", prepareSelector(value.getAsJsonPrimitive().getContent()));
            case "merkletree": {
                String merkleTreeType = getMerkleTreeType(context);
                JsonArray array = (JsonArray) value;
                List<Felt> structHashes = new ArrayList<>();
                for (JsonElement struct : array) {
                    structHashes.add(encodeValue(merkleTreeType, struct).getSecond());
                }
                Felt root = new MerkleTree(structHashes).getRootHash();
                return new Pair<>("merkletree", root);
            }
            default:
                throw new IllegalArgumentException("Type [" + typeName + "] is not defined in types.");
        }
    }

    // Implement the remaining methods as per your requirements
}