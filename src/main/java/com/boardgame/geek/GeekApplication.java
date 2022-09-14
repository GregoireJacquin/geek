package com.boardgame.geek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class GeekApplication {
	public static void main(String[] args) {
		SpringApplication.run(GeekApplication.class, args);
	}

}
