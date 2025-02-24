package com.gamesUP.gamesUP.services;

import com.gamesUP.gamesUP.model.Purchase;

import java.util.List;

public interface PurchaseService {
    List<Purchase> findAll();

    Purchase findById(Long id);

    Purchase create(Purchase purchase);

    void delete(Long id);

    Purchase partialUpdate(Long id, Purchase purchase);
}
