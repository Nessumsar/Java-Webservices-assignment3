package com.ecutb.web.controllers;

import com.ecutb.web.entities.Pokemon;
import com.ecutb.web.services.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pokemon")
@RequiredArgsConstructor
public class PokemonController {
    private final PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<Pokemon> findDitto(){
        return ResponseEntity.ok(pokemonService.dittotest());
    }
}
