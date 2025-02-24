package com.gamesUP.gamesUP.services;

import com.gamesUP.gamesUP.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);

    User create(User user);

    void delete(Long id);

    User partialUpdate(Long id, User user);
}
