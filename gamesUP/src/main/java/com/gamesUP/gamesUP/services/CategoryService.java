package com.gamesUP.gamesUP.services;

import com.gamesUP.gamesUP.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(Long id);

    Category create(Category category);

    void delete(Long id);

    Category partialUpdate(Long id, Category category);
}
