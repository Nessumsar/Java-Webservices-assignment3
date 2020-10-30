package com.ecutb.web.services;

import com.ecutb.web.entities.Pokemon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
@RequiredArgsConstructor
public class PokemonService {
    private final ConsumerService consumerService;

    public Pokemon dittotest(){
        log.info(consumerService.ditto().toString());
        return consumerService.ditto().get();
    }
}
