package com.fx.api.service;

public class UnknownPairException extends RuntimeException {

    public UnknownPairException(String pair) {
        super("Unknown pair: " + pair);
    }
}