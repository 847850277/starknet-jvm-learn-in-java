package com.swmansion.starknet.service.http;

import com.swmansion.starknet.service.http.HttpService;
import com.swmansion.starknet.service.http.HttpResponse;
import com.swmansion.starknet.provider.Request;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class HttpRequest<T> implements Request<T> {
    private HttpService.Payload payload;
    private Function<HttpResponse, T> deserializer;
    private HttpService service;

    public HttpRequest(HttpService.Payload payload, Function<HttpResponse, T> deserializer, HttpService service) {
        this.payload = payload;
        this.deserializer = deserializer;
        this.service = service;
    }

    @Override
    public T send() {
        HttpResponse response = service.send(payload);
        return deserializer.apply(response);
    }

    @Override
    public CompletableFuture<T> sendAsync() {
        return service.sendAsync(payload).thenApplyAsync(deserializer);
    }
}
