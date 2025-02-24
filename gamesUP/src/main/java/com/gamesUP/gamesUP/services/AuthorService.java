package com.gamesUP.gamesUP.services;

import com.gamesUP.gamesUP.model.Author;
import com.gamesUP.gamesUP.model.Game;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();

    Author findById(Long id);

    Author create(Author author);

    void delete(Long id);

    Author partialUpdate(Long id, Author author);
}
