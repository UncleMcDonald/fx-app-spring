package com.fx.scratch;

import org.springframework.stereotype.Service;

@Service("formal")
public class FormalGreetingService implements GreetingService {

    @Override
    public String greet(String name) {
        return "Good day, " + name + ".";
    }
}