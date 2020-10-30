package com.ecutb.web;

import com.ecutb.web.entities.Pokemon;
import com.ecutb.web.services.ConsumerService;
import com.ecutb.web.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class PokeApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(PokeApiApplication.class, args);
	}
}
