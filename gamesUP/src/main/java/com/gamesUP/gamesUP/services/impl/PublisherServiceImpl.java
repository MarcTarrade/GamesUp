package com.gamesUP.gamesUP.services.impl;

import com.gamesUP.gamesUP.exceptions.EntityDoesntExistsException;
import com.gamesUP.gamesUP.model.Author;
import com.gamesUP.gamesUP.model.Publisher;
import com.gamesUP.gamesUP.repository.PublisherRepository;
import com.gamesUP.gamesUP.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public List<Publisher> findAll() {
        List<Publisher> publishers = new ArrayList<Publisher>();
        publisherRepository.findAll().forEach(publishers::add);
        return publishers;
    }
    @Override
    public Publisher findById(Long id) {
        Optional<Publisher> optional = publisherRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityDoesntExistsException();
    }
    @Override
    public Publisher create(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public void delete(Long id) {
        publisherRepository.deleteById(id);
    }
    @Override
    public Publisher partialUpdate(Long id, Publisher newPublisher) {
        Publisher existingPublisher = findById(id);
        if(newPublisher.getName() != null) {
            existingPublisher.setName(newPublisher.getName());
        }
        if(newPublisher.getGames() != null && !newPublisher.getGames().isEmpty()) {
            existingPublisher.setGames(newPublisher.getGames());
        }
        return publisherRepository.save(existingPublisher);
    }
}
