package com.filmify.FilmiFy.Entities.Film;

import com.filmify.FilmiFy.Exceptions.FilmAlreadyExistException;
import com.filmify.FilmiFy.Exceptions.FilmNotFoundException;
import com.filmify.FilmiFy.Exceptions.GenreNotFoundException;
import com.filmify.FilmiFy.Exceptions.UserNotFoundException;
import com.filmify.FilmiFy.Models.FilmModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/film")
@CrossOrigin
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
        try{
            return filmService.getFilmForUser(user_id);
        }catch (UserNotFoundException e){
            throw e;
        }

    }

    @PostMapping("/add-film")
    public void addFilm(@RequestBody Film film, @RequestParam(name = "genre_id") List<Long> genreIds){
        try{
            filmService.addFilm(film,genreIds);
        }catch (FilmAlreadyExistException | GenreNotFoundException e){
            throw e;
        }

    }

    @GetMapping("/find-by-name")
    public ResponseEntity<?> findFilmByFilm(@RequestParam(name = "name") String name){
        try{
            return ResponseEntity.ok(filmService.findFilmByName(name));
        }catch (FilmNotFoundException e){
            throw e;
        }

    }

}
