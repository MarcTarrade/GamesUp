package com.gamesUP.gamesUP.services;

import com.gamesUP.gamesUP.model.Inventory;

import java.util.List;

public interface InventoryService {
    List<Inventory> findAll();

    Inventory findById(Long id);

    Inventory create(Inventory inventory);

    void delete(Long id);

    Inventory partialUpdate(Long id, Inventory inventory);
}
