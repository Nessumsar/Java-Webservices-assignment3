package com.ecutb.web.services;

import com.ecutb.web.entities.Pokemon;
import com.ecutb.web.repositories.PokemonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.function.Consumer;

@Service
@Slf4j
@RequiredArgsConstructor
public class PokemonService {
    private final ConsumerService consumerService;
    private final PokemonRepository pokemonRepository;

    @GetMapping
    public Pokemon findByName(String name){
        if(pokemonRepository.findByName(name).isPresent()){
            log.info("Found pokemon in mongoDb");
            return pokemonRepository.findByName(name).get();
        }
        Optional<Pokemon> pokemon = consumerService.findByName(name);
        if(pokemon.isPresent()){
            log.info("Found pokemon in pokéapi");
            pokemonRepository.save(pokemon.get());
            return pokemon.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Could not find pokemon with name %s", name));
    }
}
