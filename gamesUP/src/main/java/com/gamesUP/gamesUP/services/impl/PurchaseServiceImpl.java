package com.gamesUP.gamesUP.services.impl;

import com.gamesUP.gamesUP.exceptions.EntityDoesntExistsException;
import com.gamesUP.gamesUP.model.Author;
import com.gamesUP.gamesUP.model.Purchase;
import com.gamesUP.gamesUP.repository.PurchaseRepository;
import com.gamesUP.gamesUP.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public List<Purchase> findAll() {
        List<Purchase> purchases = new ArrayList<Purchase>();
        purchaseRepository.findAll().forEach(purchases::add);
        return purchases;
    }
    @Override
    public Purchase findById(Long id) {
        Optional<Purchase> optional = purchaseRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityDoesntExistsException();
    }
    @Override
    public Purchase create(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @Override
    public void delete(Long id) {
        purchaseRepository.deleteById(id);
    }
    @Override
    public Purchase partialUpdate(Long id, Purchase newPurchase) {
        Purchase existingPurchase = findById(id);
        if(newPurchase.getDate() != null) {
            existingPurchase.setDate(newPurchase.getDate());
        }
        if(newPurchase.getPaid() != null) {
            existingPurchase.setPaid(newPurchase.getPaid());
        }
        if(newPurchase.getDelivered() != null) {
            existingPurchase.setDelivered(newPurchase.getDelivered());
        }
        if(newPurchase.getArchived() != null) {
            existingPurchase.setArchived(newPurchase.getArchived());
        }
        if(newPurchase.getLine() != null && !newPurchase.getLine().isEmpty()) {
            existingPurchase.setLine(newPurchase.getLine());
        }
        return purchaseRepository.save(existingPurchase);
    }
}
