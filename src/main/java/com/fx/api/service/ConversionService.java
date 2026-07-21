package com.fx.api.service;

import com.fx.api.model.ConversionRequest;
import com.fx.api.model.ConversionResult;
import com.fx.api.model.Rate;
import com.fx.core.FeeCalculator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ConversionService {

    private static final List<Rate> LATEST_RATES = List.of(
            new Rate(1, "EUR", "USD", 1.0818, LocalDate.of(2026, 1, 12)),
            new Rate(2, "USD", "JPY", 156.42, LocalDate.of(2026, 1, 12)),
            new Rate(3, "GBP", "EUR", 1.1684, LocalDate.of(2026, 1, 12))
    );

    private final FeeCalculator feeCalculator = new FeeCalculator();

    public List<Rate> latestRates() {
        return LATEST_RATES;
    }

    public Rate latestRate(String base, String quote) {
        String normalizedBase = base.toUpperCase();
        String normalizedQuote = quote.toUpperCase();

        return LATEST_RATES.stream()
                .filter(rate -> rate.baseCode().equals(normalizedBase) && rate.quoteCode().equals(normalizedQuote))
                .findFirst()
                .orElseThrow(() -> new UnknownPairException(normalizedBase + "/" + normalizedQuote));
    }

    public ConversionResult convert(ConversionRequest request) {
        Rate rate = latestRate(request.base(), request.quote());
        double converted = round2(request.amount() * rate.rate());
        double fee = feeCalculator.feeFor(converted, false);
        double net = round2(converted - fee);

        return new ConversionResult(rate.pair(), request.amount(), rate.rate(), converted, fee, net);
    }

    private static double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}