package com.gamesUP.gamesUP.repository;

import com.gamesUP.gamesUP.model.Author;
import com.gamesUP.gamesUP.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
