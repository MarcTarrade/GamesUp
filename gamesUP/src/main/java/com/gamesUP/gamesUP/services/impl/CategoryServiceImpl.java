package com.gamesUP.gamesUP.services.impl;

import com.gamesUP.gamesUP.exceptions.EntityDoesntExistsException;
import com.gamesUP.gamesUP.model.Author;
import com.gamesUP.gamesUP.model.Category;
import com.gamesUP.gamesUP.repository.CategoryRepository;
import com.gamesUP.gamesUP.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        List<Category> categorys = new ArrayList<Category>();
        categoryRepository.findAll().forEach(categorys::add);
        return categorys;
    }
    @Override
    public Category findById(Long id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityDoesntExistsException();
    }
    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category partialUpdate(Long id, Category newCategory) {
        Category existingCategory = findById(id);
        if(newCategory.getType() != null) {
            existingCategory.setType(newCategory.getType());
        }
        if(newCategory.getGames() != null && !newCategory.getGames().isEmpty()) {
            existingCategory.setGames(newCategory.getGames());
        }
        return categoryRepository.save(existingCategory);
    }
}
