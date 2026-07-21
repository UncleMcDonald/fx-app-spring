package com.fx.scratch;

public final class ManualDiMain {

    private ManualDiMain() {
    }

    public static void main(String[] args) {
        GreetingService greetingService = new FriendlyGreetingService();
        GreetingConsumer consumer = new GreetingConsumer(greetingService);
        System.out.println(consumer.deliverGreeting("Taylor"));
    }
}