package com.gamesUP.gamesUP.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    @ManyToOne
    private Author auteur;
    private String genre;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Publisher publisher;
    private Integer numEdition;
    @OneToOne
    private Inventory inventory;

    public Game(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    public Game(){}
}
