package com.base.websocket.component.configure.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration(value = "executor_config")
public class Config {
    @Bean
    public ExecutorService executorService () {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        return executor;
    }
}
