package com.gamesUP.gamesUP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GamesUpApplication {
	public static void main(String[] args) {
		SpringApplication.run(GamesUpApplication.class, args);
	}

}
