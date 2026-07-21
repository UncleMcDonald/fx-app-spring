package com.fx.scratch;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = GreetingConsumer.class)
public class ScratchConfig {
}