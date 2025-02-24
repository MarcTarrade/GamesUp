package com.gamesUP.gamesUP.repository;

import com.gamesUP.gamesUP.model.Game;
import com.gamesUP.gamesUP.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNom(String nom);
}
