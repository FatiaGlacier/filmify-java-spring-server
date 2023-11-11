package com.filmify.FilmiFy.Entities.Genre;

import com.filmify.FilmiFy.Entities.User.User;
import com.filmify.FilmiFy.Entities.User.UserService;
import com.filmify.FilmiFy.Exceptions.UserNotFoundException;
import com.filmify.FilmiFy.Models.GenreModel;
import com.filmify.FilmiFy.Models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/genre")
public class GenreController {

    private final GenreService genreService;

    private final UserService userService;

    @Autowired
    public GenreController(GenreService genreService, UserService userService){
        this.genreService = genreService;
        this.userService = userService;
    }

    @GetMapping("/get-all")
    public List<GenreModel> getAll(){
        return genreService.getAll();
    }

    @GetMapping("/get-user-genre")
    public ResponseEntity<?> getUserGenres(@RequestParam(name = "user_id") Long id){
        try{
            UserModel userModel =userService.getUserById(id);
            return ResponseEntity.ok(userModel.getGenres());
        }catch (UserNotFoundException e) {
            throw e;
        }

    }
}
