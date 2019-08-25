package com.webflux.api.client;


import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientInitializer {

    public WebClientInitializer(){}

    public WebClient initialize(String url) {
        return WebClient.builder()
                .baseUrl(url)
                .defaultHeader("authorization", "test")
                .build();
    }
}
