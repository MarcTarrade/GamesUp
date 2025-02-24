package com.gamesUP.gamesUP.services.impl;

import com.gamesUP.gamesUP.exceptions.EntityDoesntExistsException;
import com.gamesUP.gamesUP.model.Author;
import com.gamesUP.gamesUP.repository.AuthorRepository;
import com.gamesUP.gamesUP.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<Author>();
        authorRepository.findAll().forEach(authors::add);
        return authors;
    }
    @Override
    public Author findById(Long id) {
        Optional<Author> optional = authorRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityDoesntExistsException();
    }
    @Override
    public Author create(Author author) {
        return authorRepository.save(author);
    }
    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
    @Override
    public Author partialUpdate(Long id, Author newAuthor) {
        Author existingAuthor = findById(id);
        if(newAuthor.getName() != null) {
            existingAuthor.setName(newAuthor.getName());
        }
        if(newAuthor.getGames() != null && !newAuthor.getGames().isEmpty()) {
            existingAuthor.setGames(newAuthor.getGames());
        }
        return authorRepository.save(existingAuthor);
    }

}
