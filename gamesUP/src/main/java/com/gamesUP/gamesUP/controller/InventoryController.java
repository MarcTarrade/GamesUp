package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.Inventory;
import com.gamesUP.gamesUP.services.InventoryService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/inventory")
    @RolesAllowed(value = {"ADMIN"})
    public List<Inventory> getAllInventory() {
        return inventoryService.findAll();
    }

    @GetMapping("/inventory/{id}")
    public Inventory getInventory(@PathVariable Long id) {
        return inventoryService.findById(id);
    }

    @PostMapping("/inventory")
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return inventoryService.create(inventory);
    }

    @PatchMapping("/inventory/{id}")
    public Inventory partialUpdate(@PathVariable Long id, @RequestBody Inventory inventory) {
        return inventoryService.partialUpdate(id, inventory);
    }

    @DeleteMapping("/inventory/{id}")
    public void delete(@PathVariable Long id){
        inventoryService.delete(id);
    }
}
