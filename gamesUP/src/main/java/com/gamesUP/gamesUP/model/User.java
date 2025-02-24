package com.gamesUP.gamesUP.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String nom;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<PurchaseLine> purchaseLine;
    @OneToMany(mappedBy = "user")
    private List<Avis> avis;
    @OneToOne
    private Wishlist wishlist;
    @ManyToOne
    private Role role;
}
