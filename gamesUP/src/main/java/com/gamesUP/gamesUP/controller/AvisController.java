package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.Avis;
import com.gamesUP.gamesUP.services.AvisService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AvisController {

    @Autowired
    private AvisService avisService;

    @GetMapping("/avis")
    @RolesAllowed(value = {"ADMIN"})
    public List<Avis> getAllAvis() {
        return avisService.findAll();
    }

    @GetMapping("/avis/{id}")
    public Avis getAvis(@PathVariable Long id) {
        return avisService.findById(id);
    }

    @PostMapping("/avis")
    public Avis addAvis(@RequestBody Avis avis) {
        return avisService.create(avis);
    }

    @PatchMapping("/avis/{id}")
    public Avis partialUpdate(@PathVariable Long id, @RequestBody Avis avis) {
        return avisService.partialUpdate(id, avis);
    }

    @DeleteMapping("/avis/{id}")
    public void delete(@PathVariable Long id){
        avisService.delete(id);
    }
}
