package com.gamesUP.gamesUP.services.impl;

import com.gamesUP.gamesUP.exceptions.EntityDoesntExistsException;
import com.gamesUP.gamesUP.model.Author;
import com.gamesUP.gamesUP.model.Avis;
import com.gamesUP.gamesUP.repository.AvisRepository;
import com.gamesUP.gamesUP.services.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AvisServiceImpl implements AvisService {
    @Autowired
    private AvisRepository avisRepository;

    @Override
    public List<Avis> findAll() {
        List<Avis> avis = new ArrayList<Avis>();
        avisRepository.findAll().forEach(avis::add);
        return avis;
    }
    @Override
    public Avis findById(Long id) {
        Optional<Avis> optional = avisRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityDoesntExistsException();
    }
    @Override
    public Avis create(Avis avis) {
        return avisRepository.save(avis);
    }

    @Override
    public void delete(Long id) {
        avisRepository.deleteById(id);
    }

    @Override
    public Avis partialUpdate(Long id, Avis newAvis) {
        Avis existingAvis = findById(id);
        if(newAvis.getCommentaire() != null) {
            existingAvis.setCommentaire(newAvis.getCommentaire());
        }
        if(newAvis.getNote() != null) {
            existingAvis.setNote(newAvis.getNote());
        }
        if(newAvis.getGame() != null) {
            existingAvis.setGame(newAvis.getGame());
        }
        if(newAvis.getUser() != null) {
            existingAvis.setUser(newAvis.getUser());
        }
        return avisRepository.save(existingAvis);
    }

}
