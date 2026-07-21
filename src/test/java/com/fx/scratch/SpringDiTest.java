package com.fx.scratch;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpringDiTest {

    @Test
    void springBuildsTheConsumer() {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(ScratchConfig.class)) {
            GreetingConsumer consumer = context.getBean(GreetingConsumer.class);

            assertEquals("Good day, Taylor.", consumer.deliverGreeting("Taylor"));
        }
    }
}