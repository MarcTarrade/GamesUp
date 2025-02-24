package com.gamesUP.gamesUP.services;

import com.gamesUP.gamesUP.model.Publisher;

import java.util.List;

public interface PublisherService {
    List<Publisher> findAll();

    Publisher findById(Long id);

    Publisher create(Publisher publisher);

    void delete(Long id);

    Publisher partialUpdate(Long id, Publisher publisher);
}
