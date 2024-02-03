package com.alexpages.secondapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class SecondappConfig 
{
    @Bean(name = "secondapp")
    public WebClient secondappWebClient() 
    {
        WebClient.Builder builder = WebClient.builder();
        //TODO baseUrl hardcoded to firstapp port, reference it in further impl
        builder.baseUrl("http://localhost:8080"); 
        return builder.build();
    }
}
