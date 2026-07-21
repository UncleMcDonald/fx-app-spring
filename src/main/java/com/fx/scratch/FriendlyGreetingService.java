package com.fx.scratch;

import org.springframework.stereotype.Service;

@Service
public class FriendlyGreetingService implements GreetingService {

    @Override
    public String greet(String name) {
        return "Hi, " + name + "!";
    }
}