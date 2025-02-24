package com.gamesUP.gamesUP.services.impl;

import com.gamesUP.gamesUP.exceptions.EntityDoesntExistsException;
import com.gamesUP.gamesUP.model.Author;
import com.gamesUP.gamesUP.model.Inventory;
import com.gamesUP.gamesUP.repository.InventoryRepository;
import com.gamesUP.gamesUP.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public List<Inventory> findAll() {
        List<Inventory> inventories = new ArrayList<Inventory>();
        inventoryRepository.findAll().forEach(inventories::add);
        return inventories;
    }
    @Override
    public Inventory findById(Long id) {
        Optional<Inventory> optional = inventoryRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityDoesntExistsException();
    }
    @Override
    public Inventory create(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public void delete(Long id) {
        inventoryRepository.deleteById(id);
    }
    @Override
    public Inventory partialUpdate(Long id, Inventory newInventory) {
        Inventory existingInventory = findById(id);
        if(newInventory.getGame() != null) {
            existingInventory.setGame(newInventory.getGame());
        }
        if(newInventory.getStock() != null) {
            existingInventory.setStock(newInventory.getStock());
        }
        return inventoryRepository.save(existingInventory);
    }
}
