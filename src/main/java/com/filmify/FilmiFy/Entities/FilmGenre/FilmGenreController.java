package com.filmify.FilmiFy.Entities.FilmGenre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/film-genre")
public class FilmGenreController {

    private final FilmGenreService filmGenreService;

    @Autowired
    public FilmGenreController(FilmGenreService filmGenreService){
        this.filmGenreService = filmGenreService;
    }
}
