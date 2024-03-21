package com.swmansion.starknet.provider;

import java.util.concurrent.CompletableFuture;

public interface Request<T> {
    /**
     * Send a request synchronously
     *
     * @return a result of the request
     */
    T send();

    /**
     * Send a request asynchronously
     *
     * @return CompletableFuture with the request result
     */
    CompletableFuture<T> sendAsync();
}
