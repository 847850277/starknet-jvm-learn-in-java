package com.swmansion.starknet.service.http;

import kotlin.Pair;
import lombok.Data;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface HttpService {

    @Data
    class Payload {
        private String url;
        private String method;
        private List<Pair<String, String>> params;
        private String body;

        public Payload(String url, String method, List<Pair<String, String>> params, String body) {
            this.url = url;
            this.method = method;
            this.params = params;
            this.body = body;
        }

        // getters and setters
    }

    /**
     * Send a synchronous http request.
     *
     * @param payload a payload to be sent
     */
    HttpResponse send(Payload payload);

    /**
     * Send an asynchronous http request.
     *
     * @param payload a payload to be sent
     */
    CompletableFuture<HttpResponse> sendAsync(Payload payload);
}
