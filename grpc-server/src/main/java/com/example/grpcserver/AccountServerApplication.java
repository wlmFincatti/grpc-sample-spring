package com.example.grpcserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
public class AccountServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountServerApplication.class);
    }

    @Bean("longTimeout")
    public RestTemplate restTemplateLongTimeout() {
        return new RestTemplateBuilder()
                .setReadTimeout(Duration.ofMillis(100000))
                .build();
    }

    @Bean("shortTimeout")
    public RestTemplate restTemplateShortTimeout() {
        return new RestTemplateBuilder()
                .setReadTimeout(Duration.ofMillis(10))
                .build();
    }
}
