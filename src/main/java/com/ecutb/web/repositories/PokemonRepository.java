package com.ecutb.web.repositories;

import com.ecutb.web.entities.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PokemonRepository extends MongoRepository<Pokemon, String> {
    Optional<Pokemon> findByName(String name);
}
