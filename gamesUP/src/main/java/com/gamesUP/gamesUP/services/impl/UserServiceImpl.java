package com.gamesUP.gamesUP.services.impl;

import com.gamesUP.gamesUP.exceptions.EntityDoesntExistsException;
import com.gamesUP.gamesUP.model.Author;
import com.gamesUP.gamesUP.model.User;
import com.gamesUP.gamesUP.repository.UserRepository;
import com.gamesUP.gamesUP.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
    @Override
    public User findById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityDoesntExistsException();
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public User partialUpdate(Long id, User newUser) {
        User existingUser = findById(id);
        if(newUser.getNom() != null) {
            existingUser.setNom(newUser.getNom());
        }
        if(newUser.getPurchaseLine() != null) {
            existingUser.setPurchaseLine(newUser.getPurchaseLine());
        }
        if(newUser.getWishlist() != null) {
            existingUser.setWishlist(newUser.getWishlist());
        }
        if(newUser.getAvis() != null && !newUser.getAvis().isEmpty()) {
            existingUser.setAvis(newUser.getAvis());
        }
        return userRepository.save(existingUser);
    }
}
