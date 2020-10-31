package com.ecutb.web.controllers;

import com.ecutb.web.entities.Pokemon;
import com.ecutb.web.services.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pokemon")
@RequiredArgsConstructor
public class PokemonController {
    private final PokemonService pokemonService;

    @GetMapping("/{name}")
    public ResponseEntity<Pokemon> findByName(@PathVariable(value = "name") String name){
        return ResponseEntity.ok(pokemonService.findByName(name));
    }
}
