package com.gamesUP.gamesUP.services.impl;

import com.gamesUP.gamesUP.exceptions.EntityDoesntExistsException;
import com.gamesUP.gamesUP.model.Author;
import com.gamesUP.gamesUP.model.Wishlist;
import com.gamesUP.gamesUP.repository.WishlistRepository;
import com.gamesUP.gamesUP.services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {
    @Autowired
    private WishlistRepository wishlistRepository;

    @Override
    public List<Wishlist> findAll() {
        List<Wishlist> wishlists = new ArrayList<Wishlist>();
        wishlistRepository.findAll().forEach(wishlists::add);
        return wishlists;
    }
    @Override
    public Wishlist findById(Long id) {
        Optional<Wishlist> optional = wishlistRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityDoesntExistsException();
    }
    @Override
    public Wishlist create(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    @Override
    public void delete(Long id) {
        wishlistRepository.deleteById(id);
    }
    @Override
    public Wishlist partialUpdate(Long id, Wishlist newWishlist) {
        Wishlist existingWishlist = findById(id);
        if(newWishlist.getUser() != null) {
            existingWishlist.setUser(newWishlist.getUser());
        }
        if(newWishlist.getGames() != null && !newWishlist.getGames().isEmpty()) {
            existingWishlist.setGames(newWishlist.getGames());
        }
        return wishlistRepository.save(existingWishlist);
    }
}
