package com.swmansion.starknet.service.http;

import com.swmansion.starknet.service.http.HttpService;
import com.swmansion.starknet.service.http.HttpResponse;
import kotlin.Pair;
import okhttp3.*;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class OkHttpService implements HttpService {
    private OkHttpClient client;
    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    public OkHttpService() {
        this(new OkHttpClient());
    }

    public OkHttpService(OkHttpClient client) {
        this.client = client;
    }

    private Request buildRequest(HttpService.Payload payload) {
        RequestBody body = payload.getBody() != null ? RequestBody.create(payload.getBody(), JSON_MEDIA_TYPE) : null;
        HttpUrl url = buildRequestUrl(payload.getUrl(), payload.getParams());

        return new Request.Builder().url(url).method(payload.getMethod(), body).build();
    }

    private HttpUrl buildRequestUrl(String baseUrl, List<Pair<String, String>> params) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder();

        for (Pair<String, String> param : params) {
            urlBuilder.addQueryParameter(param.getFirst(), param.getSecond());
        }

        return urlBuilder.build();
    }

    private HttpResponse processHttpResponse(Response response) throws IOException {
        if (response.body() == null) {
            throw new RequestFailedException("HTTP request failed with code = " + response.code(), "");
        }
        return new HttpResponse(response.isSuccessful(), response.code(), response.body().string());
    }

    @Override
    public HttpResponse send(HttpService.Payload payload) {
        Request httpRequest = buildRequest(payload);

        try (Response response = client.newCall(httpRequest).execute()) {
            return processHttpResponse(response);
        } catch (IOException e) {
            throw new RequestFailedException(e.getMessage(), "");
        }
    }

    @Override
    public CompletableFuture<HttpResponse> sendAsync(HttpService.Payload payload) {
        Request httpRequest = buildRequest(payload);

        CompletableFuture<HttpResponse> future = new CompletableFuture<>();

        client.newCall(httpRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                future.completeExceptionally(new RequestFailedException(e.getMessage(), ""));
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    HttpResponse parsedResponse = processHttpResponse(response);
                    future.complete(parsedResponse);
                } catch (Exception e) {
                    future.completeExceptionally(e);
                }
            }
        });

        return future;
    }
}
