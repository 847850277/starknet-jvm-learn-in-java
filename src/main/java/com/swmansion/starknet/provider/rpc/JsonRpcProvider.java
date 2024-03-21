package com.swmansion.starknet.provider.rpc;

import com.swmansion.starknet.data.BlockTag;
import com.swmansion.starknet.data.GetNoncePayload;
import com.swmansion.starknet.data.types.Felt;
import com.swmansion.starknet.provider.Provider;
import com.swmansion.starknet.provider.Request;
import com.swmansion.starknet.service.http.HttpRequest;
import com.swmansion.starknet.service.http.HttpService;
import com.swmansion.starknet.service.http.OkHttpService;
import kotlin.Lazy;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import com.swmansion.starknet.data.Tag;
import com.swmansion.starknet.data.Number;
import com.swmansion.starknet.data.Hash;

import java.util.Map;
import java.util.Set;


public class JsonRpcProvider implements Provider {
    private String url;
    private HttpService httpService;
    private boolean ignoreUnknownJsonKeys;

    private final Json deserializationJson;

    public JsonRpcProvider(String url, HttpService httpService, boolean ignoreUnknownJsonKeys) {
        this.url = url;
        this.httpService = httpService;
        this.ignoreUnknownJsonKeys = ignoreUnknownJsonKeys;
        this.deserializationJson =  (Json)Json.Default;
    }



    public JsonRpcProvider(String url, boolean ignoreUnknownJsonKeys) {
        this(url, new OkHttpService(), ignoreUnknownJsonKeys);
    }

    public JsonRpcProvider(String url, HttpService httpService) {
        this(url, httpService, false);
    }

    public JsonRpcProvider(String url) {
        this(url, new OkHttpService(), false);
    }

    private Request<Felt> getNonce(GetNoncePayload payload) {
        KType var10001 = Reflection.typeOf(GetNoncePayload.class);
        SerializationStrategy serializer = (SerializationStrategy) SerializersKt.serializer(Json.Default.getSerializersModule(), var10001);
        JsonElement jsonPayload = Json.Default.encodeToJsonElement(serializer,payload);
        return buildRequest(JsonRpcMethod.GET_NONCE, jsonPayload, Felt.Companion.serializer());
    }

    private final Map buildRequestJson(String method, JsonElement paramsJson) {
        Map map = MapsKt.mapOf(new Pair[]{
                TuplesKt.to("jsonrpc", JsonElementKt.JsonPrimitive("2.0")),
                TuplesKt.to("method", JsonElementKt.JsonPrimitive(method)),
                TuplesKt.to("id", JsonElementKt.JsonPrimitive(0)),
                TuplesKt.to("params", paramsJson)});
        return (Map) (new JsonObject(map));
    }

    private final HttpRequest buildRequest(JsonRpcMethod method, JsonElement paramsJson, KSerializer responseSerializer) {
        Map requestJson = this.buildRequestJson(method.getMethodName(), paramsJson);
        HttpService.Payload payload = new HttpService.Payload(this.url, "POST", CollectionsKt.emptyList(), requestJson.toString());
        return new HttpRequest(payload, BuildJsonHttpDeserializerKt.buildJsonHttpDeserializer(responseSerializer, this.deserializationJson), this.httpService);
    }



    @Override
    public Request<Felt> getNonce(Felt contractAddress) {
        return getNonce(contractAddress, BlockTag.PENDING);
    }

    @Override
    public Request<Felt> getNonce(Felt contractAddress, BlockTag blockTag) {
        GetNoncePayload payload = new GetNoncePayload(contractAddress, new Tag(blockTag));

        return getNonce(payload);
    }

    @Override
    public Request<Felt> getNonce(Felt contractAddress, int blockNumber) {
        GetNoncePayload payload = new GetNoncePayload(contractAddress, new Number(blockNumber));

        return getNonce(payload);
    }

    @Override
    public Request<Felt> getNonce(Felt contractAddress, Felt blockHash) {
        GetNoncePayload payload = new GetNoncePayload(contractAddress, new Hash(blockHash));

        return getNonce(payload);
    }

    // Rest of the class implementation goes here
}
