package com.filmify.FilmiFy.Entities.Film;

import com.filmify.FilmiFy.Models.FilmModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/film")
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService){
        this.filmService = filmService;
    }

    @GetMapping("/get-all")
    public List<FilmModel> getAll(){
        return filmService.getAll();
    }

    @GetMapping("/get-film-for-user")
    public List<FilmModel> getFilmForUser(@RequestParam(name = "user_id") Long user_id){
        return filmService.getFilmForUser(user_id);
    }

}
