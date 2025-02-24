package com.gamesUP.gamesUP.services;

import com.gamesUP.gamesUP.model.Wishlist;

import java.util.List;

public interface WishlistService {
    List<Wishlist> findAll();

    Wishlist findById(Long id);

    Wishlist create(Wishlist wishlist);

    void delete(Long id);

    Wishlist partialUpdate(Long id, Wishlist wishlist);
}
