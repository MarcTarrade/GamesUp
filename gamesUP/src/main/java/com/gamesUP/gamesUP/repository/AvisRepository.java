package com.gamesUP.gamesUP.repository;

import com.gamesUP.gamesUP.model.Avis;
import com.gamesUP.gamesUP.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisRepository extends CrudRepository<Avis, Long> {
}
