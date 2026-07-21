package com.fx.api.model;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record ConversionRequest(
        @Pattern(regexp = "[A-Z]{3}", message = "must be a 3-letter uppercase currency code")
        String base,
        @Pattern(regexp = "[A-Z]{3}", message = "must be a 3-letter uppercase currency code")
        String quote,
        @Positive
        double amount
) {
}