package com.fx.scratch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ManualDiTest {

    @Test
    void consumerUsesInjectedService() {
        GreetingConsumer consumer = new GreetingConsumer(new FriendlyGreetingService());

        assertEquals("Hi, Taylor!", consumer.deliverGreeting("Taylor"));
    }
}