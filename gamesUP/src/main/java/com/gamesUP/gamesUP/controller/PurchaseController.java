package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.Purchase;
import com.gamesUP.gamesUP.services.PurchaseService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/purchase")
    @RolesAllowed(value = {"ADMIN"})
    public List<Purchase> getAllPurchase() {
        return purchaseService.findAll();
    }

    @GetMapping("/purchase/{id}")
    public Purchase getPurchase(@PathVariable Long id) {
        return purchaseService.findById(id);
    }

    @PostMapping("/purchase")
    public Purchase addPurchase(@RequestBody Purchase purchase) {
        return purchaseService.create(purchase);
    }

    @PatchMapping("/purchase/{id}")
    public Purchase partialUpdate(@PathVariable Long id, @RequestBody Purchase purchase) {
        return purchaseService.partialUpdate(id, purchase);
    }

    @DeleteMapping("/purchase/{id}")
    public void delete(@PathVariable Long id){
        purchaseService.delete(id);
    }
}
