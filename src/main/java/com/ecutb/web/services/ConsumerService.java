package com.ecutb.web.services;

import com.ecutb.web.entities.Pokemon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
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

    public Optional<Pokemon> findByName(String name){
        log.warn(name);
        var url = baseUrl + "pokemon/" + name;
        return Optional.ofNullable(restTemplate.getForObject(url, Pokemon.class));
    }
}
