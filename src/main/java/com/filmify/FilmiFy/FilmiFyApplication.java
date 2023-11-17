package com.filmify.FilmiFy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FilmiFyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmiFyApplication.class, args);
	}

}
