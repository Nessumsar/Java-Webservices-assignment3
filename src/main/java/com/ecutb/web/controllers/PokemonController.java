package com.ecutb.web.controllers;

import com.ecutb.web.entities.Pokemon;
import com.ecutb.web.services.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemon")
@RequiredArgsConstructor
public class PokemonController {
    private final PokemonService pokemonService;

    @GetMapping("/open")
    public ResponseEntity<List<Pokemon>> findAll(@RequestParam(required = false) String name,
                                                 @RequestParam(required = false) Boolean sortByHeight,
                                                 @RequestParam(required = false) Boolean sortByWeight,
                                                 @RequestParam(required = false) Boolean sortById,
                                                 @RequestParam(required = false) Integer page){
        return ResponseEntity.ok(pokemonService.findAll(name,sortByHeight, sortByWeight, sortById, page));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Pokemon> findByName(@PathVariable(value = "name") String name){
        return ResponseEntity.ok(pokemonService.findByName(name));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Pokemon> findById(@PathVariable(value = "id") String id){
        return ResponseEntity.ok(pokemonService.findById(id));
    }
}
