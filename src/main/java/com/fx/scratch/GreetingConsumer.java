package com.fx.scratch;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GreetingConsumer {

    private final GreetingService greetingService;

    public GreetingConsumer(@Qualifier("formal") GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String deliverGreeting(String name) {
        return greetingService.greet(name);
    }
}