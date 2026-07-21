package com.fx.api.service;

import com.fx.api.model.ConversionRequest;
import com.fx.api.model.ConversionResult;
import com.fx.api.model.Rate;
import com.fx.api.repo.RateRepository;
import com.fx.core.FeeCalculator;
import org.springframework.stereotype.Service;


@Service
public class ConversionService {

    private final RateRepository rates;

    public ConversionService(RateRepository rates) {
        this.rates = rates;
    }

    public ConversionResult convert(ConversionRequest request) {
        String base = request.base().toUpperCase();
        String quote = request.quote().toUpperCase();
        Rate rate = rates.findLatestForPair(base, quote)
                .orElseThrow(() -> new UnknownPairException(base + "/" + quote));
        double converted = round2(request.amount() * rate.rate());
        double fee = new FeeCalculator().feeFor(converted, false);
        double net = round2(converted - fee);

        return new ConversionResult(rate.pair(), request.amount(), rate.rate(), converted, fee, net);
    }

    private static double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}