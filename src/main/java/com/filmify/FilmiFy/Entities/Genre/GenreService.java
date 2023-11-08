package com.filmify.FilmiFy.Entities.Genre;

import com.filmify.FilmiFy.Models.GenreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    public List<GenreModel> getAll() {
        List<Genre> genres = genreRepository.findAll();
        List<GenreModel> genreModels = new ArrayList<>();

        for(Genre genre: genres){
            genreModels.add(GenreModel.toModel(genre));
        }

        return genreModels;
    }
}
