package com.gamesUP.gamesUP.services;

import com.gamesUP.gamesUP.model.PurchaseLine;

import java.util.List;

public interface PurchaseLineService {
    List<PurchaseLine> findAll();

    PurchaseLine findById(Long id);

    PurchaseLine create(PurchaseLine purchaseLine);

    void delete(Long id);

    PurchaseLine partialUpdate(Long id, PurchaseLine purchaseLine);
}
