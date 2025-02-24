package com.gamesUP.gamesUP.services;

import com.gamesUP.gamesUP.model.Game;

import java.util.List;

public interface GameService {
    List<Game> findAll();

    List<Game> searchGames(String nom, String auteur, String genre, String category, String publisher);

    Game findById(Long id);

    Game create(Game game);

    void delete(Long id);

    Game partialUpdate(Long id, Game game);
}
