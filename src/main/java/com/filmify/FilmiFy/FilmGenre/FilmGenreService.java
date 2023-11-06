package com.filmify.FilmiFy.FilmGenre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmGenreService {

    private FilmGenreRepository filmGenreRepository;

    @Autowired
    public FilmGenreService(FilmGenreRepository filmGenreRepository){
        this.filmGenreRepository = filmGenreRepository;
    }
}
