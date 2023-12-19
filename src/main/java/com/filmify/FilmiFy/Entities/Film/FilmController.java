package com.filmify.FilmiFy.Entities.Film;

import com.filmify.FilmiFy.Exceptions.*;
import com.filmify.FilmiFy.Models.FilmModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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

//    @GetMapping("/find-by-name")
//    public ResponseEntity<Set<FilmModel>> findFilmByName(@RequestParam(name = "name") String name){
//        try{
//            return ResponseEntity.ok(filmService.findFilmByName(name));
//        }catch (FilmNotFoundException e){
//            throw e;
//        }
//
//    }

    @GetMapping("/find-by-name")
    public ResponseEntity<FilmModel> findFilmByName(@RequestParam(name = "name") String name){
        try{
            return ResponseEntity.ok(filmService.findFilmByName(name));
        }catch (FilmNotFoundException e){
            throw e;
        }

    }

    @GetMapping("/film-for-room")
    public ResponseEntity<List<FilmModel>> getFilmForRoom(@RequestParam(name = "code") String code){
        try{

            return ResponseEntity.ok(filmService.getFilmForRoom(code));
        }catch (RoomNotFoundException e){
            throw e;
        }

    }

}
