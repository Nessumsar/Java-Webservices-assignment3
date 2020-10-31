package com.ecutb.web.services;

import com.ecutb.web.entities.Pokemon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

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
        var url = baseUrl + "pokemon/" + name;
        try{
            Optional<Pokemon> pokemon = Optional.ofNullable(restTemplate.getForObject(url, Pokemon.class));
            return pokemon;
        }catch(Exception e){
            log.warn(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find pokemon with name in pokeapi %s", name));
        }
    }

    public Optional<Pokemon> findById(String id){
        var url = baseUrl + "pokemon/" + id;
        try{
            Optional<Pokemon> pokemon = Optional.ofNullable(restTemplate.getForObject(url, Pokemon.class));
            return pokemon;
        }catch(Exception e){
            log.warn(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find pokemon with id in pokeapi %s", id));
        }
    }
}
