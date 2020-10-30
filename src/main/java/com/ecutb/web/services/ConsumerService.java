package com.ecutb.web.services;

import com.ecutb.web.entities.Pokemon;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@ConfigurationProperties(value = "ecutb.web", ignoreUnknownFields = false)
public class ConsumerService {
    private RestTemplate restTemplate;
    private String baseUrl;

    public ConsumerService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }

    public void setBaseUrl(String baseUrl){
        this.baseUrl = baseUrl;
    }

    public Optional<Pokemon> ditto(){
        var url = baseUrl + "pokemon/ditto";
        return Optional.ofNullable(restTemplate.getForObject(url, Pokemon.class));
    }
}
