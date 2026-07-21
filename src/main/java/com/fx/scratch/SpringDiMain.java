package com.fx.scratch;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class SpringDiMain {

    private SpringDiMain() {
    }

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(ScratchConfig.class)) {
            GreetingConsumer consumer = context.getBean(GreetingConsumer.class);
            System.out.println(consumer.deliverGreeting("Taylor"));
        }
    }
}