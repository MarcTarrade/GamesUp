package com.gamesUP.gamesUP.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    @OneToMany
    private List<Game> games;

    public Wishlist(Long id, User user) {
        this.id = id;
        this.user = user;
    }
    public Wishlist(){}
}
