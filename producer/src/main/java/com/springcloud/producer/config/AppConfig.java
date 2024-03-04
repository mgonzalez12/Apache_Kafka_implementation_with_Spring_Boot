package com.springcloud.producer.config;

import com.springcloud.producer.events.StockEventProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public StockEventProducer stockEventProducer() {
        return new StockEventProducer();
    }
}

