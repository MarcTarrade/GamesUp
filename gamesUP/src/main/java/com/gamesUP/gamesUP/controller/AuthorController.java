package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.Author;
import com.gamesUP.gamesUP.services.AuthorService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/author")
    @RolesAllowed(value = {"ADMIN"})
    public List<Author> getAllAuthor() {
        return authorService.findAll();
    }

    @GetMapping("/author/{id}")
    public Author getAuthor(@PathVariable Long id) {
        return authorService.findById(id);
    }

    @PostMapping("/author")
    public Author addAuthor(@RequestBody Author author) {
        return authorService.create(author);
    }

    @PatchMapping("/author/{id}")
    public Author partialUpdate(@PathVariable Long id, @RequestBody Author author) {
        return authorService.partialUpdate(id, author);
    }

    @DeleteMapping("/author/{id}")
    public void delete(@PathVariable Long id){
        authorService.delete(id);
    }
}
