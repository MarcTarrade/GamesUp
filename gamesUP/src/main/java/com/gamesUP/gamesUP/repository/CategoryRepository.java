package com.gamesUP.gamesUP.repository;

import com.gamesUP.gamesUP.model.Category;
import com.gamesUP.gamesUP.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
