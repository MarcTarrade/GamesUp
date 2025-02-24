package com.gamesUP.gamesUP.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
public class PurchaseLine {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @ManyToOne
    private User user;
    @OneToOne
    private Game game;
    private Float prix;
    @ManyToOne
    private Purchase purchase;

}
