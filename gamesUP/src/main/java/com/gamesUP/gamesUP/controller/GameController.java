package com.gamesUP.gamesUP.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.gamesUP.gamesUP.model.Game;
import com.gamesUP.gamesUP.services.GameService;
import com.gamesUP.gamesUP.services.GameService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.gamesUP.gamesUP.model.Game;

@RestController
public class GameController {
    @Autowired
    private GameService gameService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/game")
    @RolesAllowed(value = {"ADMIN"})
    public List<Game> getAllGame() {
        return gameService.findAll();
    }

    @GetMapping("/gameSearch")
    public List<Game> searchGames(@RequestParam(value = "nom", required = false) String nom, @RequestParam(value = "auteur", required = false) String auteur, @RequestParam(value = "genre", required = false) String genre, @RequestParam(value = "category", required = false) String category, @RequestParam(value = "publisher", required = false) String publisher) {
        return gameService.searchGames(nom, auteur, genre, category, publisher);
    }

    @GetMapping("/game/{id}")
    public Game getGame(@PathVariable Long id) {
        return gameService.findById(id);
    }

    @PostMapping("/game")
    public Game addGame(@RequestBody Game game) {
        return gameService.create(game);
    }

    @PatchMapping("/game/{id}")
    public Game partialUpdate(@PathVariable Long id, @RequestBody Game game) {
        return gameService.partialUpdate(id, game);
    }

    @DeleteMapping("/game/{id}")
    public void delete(@PathVariable Long id){
        gameService.delete(id);
    }
}