package com.gamesUP.gamesUP.services.impl;

import com.gamesUP.gamesUP.exceptions.EntityDoesntExistsException;
import com.gamesUP.gamesUP.model.Author;
import com.gamesUP.gamesUP.model.PurchaseLine;
import com.gamesUP.gamesUP.repository.PurchaseLineRepository;
import com.gamesUP.gamesUP.services.PurchaseLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseLineServiceImpl implements PurchaseLineService {
    @Autowired
    private PurchaseLineRepository purchaseLineRepository;

    @Override
    public List<PurchaseLine> findAll() {
        List<PurchaseLine> purchaseLines = new ArrayList<PurchaseLine>();
        purchaseLineRepository.findAll().forEach(purchaseLines::add);
        return purchaseLines;
    }
    @Override
    public PurchaseLine findById(Long id) {
        Optional<PurchaseLine> optional = purchaseLineRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityDoesntExistsException();
    }
    @Override
    public PurchaseLine create(PurchaseLine purchaseLine) {
        return purchaseLineRepository.save(purchaseLine);
    }

    @Override
    public void delete(Long id) {
        purchaseLineRepository.deleteById(id);
    }
    @Override
    public PurchaseLine partialUpdate(Long id, PurchaseLine newPurchaseLine) {
        PurchaseLine existingPurchaseLine = findById(id);
        if(newPurchaseLine.getUser() != null) {
            existingPurchaseLine.setUser(newPurchaseLine.getUser());
        }
        if(newPurchaseLine.getGame() != null) {
            existingPurchaseLine.setGame(newPurchaseLine.getGame());
        }
        if(newPurchaseLine.getPrix() != null) {
            existingPurchaseLine.setPrix(newPurchaseLine.getPrix());
        }
        if(newPurchaseLine.getPurchase() != null) {
            existingPurchaseLine.setPurchase(newPurchaseLine.getPurchase());
        }
        return purchaseLineRepository.save(existingPurchaseLine);
    }
}
