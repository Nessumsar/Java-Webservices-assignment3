package com.ecutb.web.services;

import com.ecutb.web.entities.Pokemon;
import com.ecutb.web.repositories.PokemonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PokemonService {
    private final ConsumerService consumerService;
    private final PokemonRepository pokemonRepository;

    @GetMapping
    public List<Pokemon> findAll(String name,
                                 Boolean sortByHeight,
                                 Boolean sortByWeight,
                                 Boolean sortById,
                                 Integer page){
        if(page == null){
            page = 0;
        }

        List<Pokemon> pokemons = pokemonRepository.findAll();

        if(name != null){
            log.info("Finding pokemons with matching names");
            pokemons = pokemons.stream()
                    .filter(p -> p.getName()
                            .toLowerCase()
                            .contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if(sortByHeight != null){
            log.info("Sorting by height...");
            pokemons = pokemons.stream()
                    .sorted(Comparator.comparing(Pokemon::getHeight))
                    .collect(Collectors.toList());
        }
        if(sortByWeight != null){
            log.info("Sorting by weight...");
            pokemons = pokemons.stream()
                    .sorted(Comparator.comparingInt(Pokemon::getWeight))
                    .collect(Collectors.toList());
        }
        if(sortById != null){
            log.info("Sorting by id...");
            pokemons = pokemons.stream()
                    .sorted(Comparator.comparingInt(o -> Integer.parseInt(o.getId())))
                    .collect(Collectors.toList());
        }


        PagedListHolder<Pokemon> pokemonPage = new PagedListHolder<>(pokemons);
        pokemonPage.setPageSize(100);
        pokemonPage.setPage(page);

        pokemons = pokemonPage.getPageList();

        return pokemons;
    }

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

    @GetMapping
    public Pokemon findById(String id){
        if(pokemonRepository.existsById(id)){
            log.info("Found pokemon in mongoDb");
            return pokemonRepository.findById(id).get();
        }
        Optional<Pokemon> pokemon = consumerService.findById(id);
        if(pokemon.isPresent()){
            log.info("Found pokemon in pokéapi");
            pokemonRepository.save(pokemon.get());
            return pokemon.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Could not find pokemon with id %s", id));
    }
}
