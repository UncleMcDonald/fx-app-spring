package com.fx.api.model;

import java.time.LocalDate;

public record Rate(int id, String baseCode, String quoteCode, double rate, LocalDate rateDate) {

    public String pair() {
        return baseCode + "/" + quoteCode;
    }
}