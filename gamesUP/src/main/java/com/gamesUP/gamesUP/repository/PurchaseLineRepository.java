package com.gamesUP.gamesUP.repository;

import com.gamesUP.gamesUP.model.PurchaseLine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseLineRepository extends CrudRepository<PurchaseLine, Long> {
}
