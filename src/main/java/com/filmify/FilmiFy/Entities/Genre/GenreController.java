package com.filmify.FilmiFy.Entities.Genre;

import com.filmify.FilmiFy.Models.GenreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/genre")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService){
        this.genreService = genreService;
    }

    @GetMapping("/get-all")
    public List<GenreModel> getAll(){
        return genreService.getAll();
    }
}
