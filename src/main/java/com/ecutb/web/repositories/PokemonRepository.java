package com.ecutb.web.repositories;

import com.ecutb.web.entities.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PokemonRepository extends MongoRepository<Pokemon, String> {
}
