package com.gamesUP.gamesUP.services.impl;

import com.gamesUP.gamesUP.exceptions.EntityDoesntExistsException;
import com.gamesUP.gamesUP.model.Author;
import com.gamesUP.gamesUP.model.Game;
import com.gamesUP.gamesUP.repository.GameRepository;
import com.gamesUP.gamesUP.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<Game> findAll() {
        List<Game> games = new ArrayList<Game>();
        gameRepository.findAll().forEach(games::add);
        return games;
    }
    @Override
    public List<Game> searchGames(String nom, String auteur, String genre, String category, String publisher) {
        return gameRepository.findByNomOrAuteurNameOrGenreOrCategoryTypeOrPublisherName(nom, auteur, genre, category, publisher);
    }
    @Override
    public Game findById(Long id) {
        Optional<Game> optional = gameRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityDoesntExistsException();
    }
    @Override
    public Game create(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public void delete(Long id) {
        gameRepository.deleteById(id);
    }
    @Override
    public Game partialUpdate(Long id, Game newGame) {
        Game existingGame = findById(id);
        if(newGame.getNom() != null) {
            existingGame.setNom(newGame.getNom());
        }
        if(newGame.getAuteur() != null) {
            existingGame.setAuteur(newGame.getAuteur());
        }
        if(newGame.getGenre() != null) {
            existingGame.setGenre(newGame.getGenre());
        }
        if(newGame.getCategory() != null) {
            existingGame.setCategory(newGame.getCategory());
        }
        if(newGame.getPublisher() != null) {
            existingGame.setPublisher(newGame.getPublisher());
        }
        if(newGame.getNumEdition() != null) {
            existingGame.setNumEdition(newGame.getNumEdition());
        }
        if(newGame.getInventory() != null) {
            existingGame.setInventory(newGame.getInventory());
        }
        return gameRepository.save(existingGame);
    }
}
