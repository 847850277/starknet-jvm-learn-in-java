package com.swmansion.starknet.provider.rpc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swmansion.starknet.service.http.HttpResponse;
import com.swmansion.starknet.service.http.RequestFailedException;
import com.swmansion.starknet.service.http.RpcRequestFailedException;

import java.util.function.Function;

public class JsonHttpDeserializer<T> {
    private Class<T> deserializationClass;
    private ObjectMapper objectMapper;

    public JsonHttpDeserializer(Class<T> deserializationClass) {
        this.deserializationClass = deserializationClass;
        this.objectMapper = new ObjectMapper();
    }

    public Function<HttpResponse, T> getDeserializer() {
        return response -> {
            if (!response.isSuccessful()) {
                throw new RequestFailedException("Request failed with code " + response.getCode(), response.getBody());
            }

            JsonRpcResponse<T> jsonRpcResponse;
            try {
                jsonRpcResponse = objectMapper.readValue(response.getBody(), objectMapper.getTypeFactory().constructParametricType(JsonRpcResponse.class, deserializationClass));
            } catch (Exception e) {
                throw new RuntimeException("Failed to deserialize response", e);
            }

            if (jsonRpcResponse.getError() != null) {
                throw new RpcRequestFailedException(
                        jsonRpcResponse.getError().getCode(),
                        jsonRpcResponse.getError().getMessage(),
                        jsonRpcResponse.getError().getData(),
                        response.getBody()
                );
            }

            if (jsonRpcResponse.getResult() == null) {
                throw new RequestFailedException("Response did not contain a result", response.getBody());
            }

            return jsonRpcResponse.getResult();
        };
    }
}
