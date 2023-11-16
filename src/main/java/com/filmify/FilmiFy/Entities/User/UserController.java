package com.filmify.FilmiFy.Entities.User;

import com.filmify.FilmiFy.Entities.Genre.Genre;
import com.filmify.FilmiFy.Exceptions.GenreNotFoundException;
import com.filmify.FilmiFy.Exceptions.UserAlreadyExistsException;
import com.filmify.FilmiFy.Exceptions.UserNotFoundException;
import com.filmify.FilmiFy.Exceptions.WrongPasswordException;
import com.filmify.FilmiFy.Models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/get-all")
    public List<UserModel> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/add-user")
    public ResponseEntity<?> registerNewUser(@RequestBody User user){
        try{
            userService.addNewUser(user);
            return ResponseEntity.ok(UserModel.toModel(user));
        }catch (UserAlreadyExistsException e){
            throw e;
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam(name = "email") String email,
                                   @RequestParam(name = "password") String password){
        try {
            UserModel user = userService.login(email, password);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException | WrongPasswordException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PutMapping("/add-user-fav-genre")
    public ResponseEntity<?> addUserFavoriteGenre(@RequestBody List<Genre> genres,
                                     @RequestParam(name = "user_id") Long user_id){
        try{
            userService.addUserFavoriteGenres(genres, user_id);
            return ResponseEntity.ok(userService.getUserById(user_id));
        }catch (UserNotFoundException | GenreNotFoundException e){
            throw e;
        }

    }

    @GetMapping("/get-user-genre")
    public ResponseEntity<?> getUserGenres(@RequestParam(name = "user_id") Long id){
        try{
            UserModel userModel = userService.getUserById(id);
            return ResponseEntity.ok(userModel.getGenres());
        }catch (UserNotFoundException e){
            throw e;
        }

    }
}
