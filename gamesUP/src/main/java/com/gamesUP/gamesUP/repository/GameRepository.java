package com.gamesUP.gamesUP.repository;

import com.gamesUP.gamesUP.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    List<Game> findByNomOrAuteurNameOrGenreOrCategoryTypeOrPublisherName(String nom, String auteur, String genre, String category, String publisher);

}
