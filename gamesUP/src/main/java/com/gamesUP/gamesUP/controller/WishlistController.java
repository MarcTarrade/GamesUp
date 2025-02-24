package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.Wishlist;
import com.gamesUP.gamesUP.services.WishlistService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @GetMapping("/wishlist")
    @RolesAllowed(value = {"ADMIN"})
    public List<Wishlist> getAllWishlist() {
        return wishlistService.findAll();
    }

    @GetMapping("/wishlist/{id}")
    public Wishlist getWishlist(@PathVariable Long id) {
        return wishlistService.findById(id);
    }

    @PostMapping("/wishlist")
    public Wishlist addWishlist(@RequestBody Wishlist wishlist) {
        return wishlistService.create(wishlist);
    }

    @PatchMapping("/wishlist/{id}")
    public Wishlist partialUpdate(@PathVariable Long id, @RequestBody Wishlist wishlist) {
        return wishlistService.partialUpdate(id, wishlist);
    }

    @DeleteMapping("/wishlist/{id}")
    public void delete(@PathVariable Long id){
        wishlistService.delete(id);
    }
}
