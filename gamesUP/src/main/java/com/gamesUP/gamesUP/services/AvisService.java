package com.gamesUP.gamesUP.services;

import com.gamesUP.gamesUP.model.Avis;

import java.util.List;

public interface AvisService {
    List<Avis> findAll();
    
    Avis findById(Long id);
    
    Avis create(Avis avis);
    
    void delete(Long id);

    Avis partialUpdate(Long id, Avis avis);
}
