package com.gamesUP.gamesUP.controller;

import com.gamesUP.gamesUP.model.Publisher;
import com.gamesUP.gamesUP.services.PublisherService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping("/publisher")
    @RolesAllowed(value = {"ADMIN"})
    public List<Publisher> getAllPublisher() {
        return publisherService.findAll();
    }

    @GetMapping("/publisher/{id}")
    public Publisher getPublisher(@PathVariable Long id) {
        return publisherService.findById(id);
    }

    @PostMapping("/publisher")
    public Publisher addPublisher(@RequestBody Publisher publisher) {
        return publisherService.create(publisher);
    }

    @PatchMapping("/publisher/{id}")
    public Publisher partialUpdate(@PathVariable Long id, @RequestBody Publisher publisher) {
        return publisherService.partialUpdate(id, publisher);
    }

    @DeleteMapping("/publisher/{id}")
    public void delete(@PathVariable Long id){
        publisherService.delete(id);
    }
}
