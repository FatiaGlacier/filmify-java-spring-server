package com.filmify.FilmiFy.Entities.User;

import com.filmify.FilmiFy.Entities.Genre.Genre;
import com.filmify.FilmiFy.Exceptions.*;
import com.filmify.FilmiFy.Models.FilmModel;
import com.filmify.FilmiFy.Models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/user")
@CrossOrigin
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
    public ResponseEntity<?> addUserFavoriteGenre(@RequestBody Set<Genre> genres,
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

    @PostMapping("/add-liked-film")
    public ResponseEntity<UserModel> addLikedFilm(@RequestParam(name = "user_id") Long user_id,
                                                  @RequestParam(name = "film_id") Long film_id){
        try{
            UserModel userModel = userService.addLikedFilm(user_id, film_id);
            return ResponseEntity.ok(userModel);
        }catch (UserNotFoundException | FilmNotFoundException e){
            throw e;
        }
    }

    @DeleteMapping("/delete-liked-film")
    public ResponseEntity<UserModel> deleteLikedFilm(@RequestParam(name = "user_id") Long user_id,
                                                     @RequestParam(name = "film_id") Long film_id){
        try{
            UserModel userModel = userService.deleteLikedFilm(user_id, film_id);
            return ResponseEntity.ok(userModel);
        }catch (UserNotFoundException | FilmNotFoundException e){
            throw e;
        }
    }

    @GetMapping("/get-user-films")
    public ResponseEntity<Set<FilmModel>> getUserFilms(@RequestParam(name = "user_id") Long user_id){
        try{
            Set<FilmModel> filmModels = userService.getUserFilms(user_id);
            return ResponseEntity.ok(filmModels);
        }catch (UserNotFoundException | FilmNotFoundException e){
            throw e;
        }
    }
    @PutMapping("/change-userInfo")
    public ResponseEntity<?> changeUserInfo(@RequestBody UserModel user, @RequestParam(name = "user_id") Long id){
        try{
            UserModel userModel = userService.changeUserInfo(user, id);
            return ResponseEntity.ok(userModel);
        }catch (UserNotFoundException e){
            throw e;
        }
    }

    @GetMapping("/isInUserList")
    public ResponseEntity<Boolean> isInUserList(@RequestParam(name = "user_id") Long user_id,
                                                @RequestParam(name = "film_id") Long film_id ){
        try{
            Boolean isPresent = userService.isInUserList(user_id, film_id);
            return ResponseEntity.ok(isPresent);
        }catch (UserNotFoundException | FilmNotFoundException e){
            throw e;
        }
    }
}
