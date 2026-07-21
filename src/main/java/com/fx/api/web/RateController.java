package com.fx.api.web;

import com.fx.api.model.ConversionRequest;
import com.fx.api.model.ConversionResult;
import com.fx.api.model.Rate;
import com.fx.api.repo.RateRepository;
import com.fx.api.service.ConversionService;
import com.fx.api.service.UnknownPairException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RateController {

    private final RateRepository rates;
    private final ConversionService conversionService;

    public RateController(RateRepository rates, ConversionService conversionService) {
        this.rates = rates;
        this.conversionService = conversionService;
    }

    @GetMapping("/rates")
    public List<Rate> rates() {
        return rates.findLatest();
    }

    @GetMapping("/rates/{base}/{quote}")
    public Rate rate(@PathVariable String base, @PathVariable String quote) {
        String normalizedBase = base.toUpperCase();
        String normalizedQuote = quote.toUpperCase();
        return rates.findLatestForPair(normalizedBase, normalizedQuote)
                .orElseThrow(() -> new UnknownPairException(normalizedBase + "/" + normalizedQuote));
    }

    @PostMapping("/conversions")
    @ResponseStatus(HttpStatus.CREATED)
    public ConversionResult convert(@RequestBody @Valid ConversionRequest request) {
        return conversionService.convert(request);
    }
}