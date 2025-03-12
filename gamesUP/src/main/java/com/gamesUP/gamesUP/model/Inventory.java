package com.gamesUP.gamesUP.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@Entity
@Data
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private Game game;
	private Integer stock;

	public Inventory(Long id, Game game) {
		this.id = id;
		this.game = game;
	}
	public Inventory(){}
}
