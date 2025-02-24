package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.Category;
import com.gamesUP.gamesUP.services.CategoryService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    @RolesAllowed(value = {"ADMIN"})
    public List<Category> getAllCategory() {
        return categoryService.findAll();
    }

    @GetMapping("/category/{id}")
    public Category getCategory(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PostMapping("/category")
    public Category addCategory(@RequestBody Category category) {
        return categoryService.create(category);
    }

    @PatchMapping("/category/{id}")
    public Category partialUpdate(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.partialUpdate(id, category);
    }

    @DeleteMapping("/category/{id}")
    public void delete(@PathVariable Long id){
        categoryService.delete(id);
    }
}
