package com.filmify.FilmiFy.Entities.User;

import com.filmify.FilmiFy.Entities.Genre.Genre;
import com.filmify.FilmiFy.Models.UserModel;
import jakarta.persistence.EntityNotFoundException;
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
    public void registerNewUser(@RequestBody User user){
        userService.addNewUser(user);
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam(name = "user_name") String user_name, @RequestParam(name = "password") String password){
        try {
            UserModel user = userService.login(user_name, password);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PutMapping("/add-user-fav-genre")
    public void addUserFavoriteGenre(@RequestBody List<Genre> genres, @RequestParam(name = "user_id") Long user_id){
        userService.addUserFavoriteGenres(genres, user_id);
    }

    @GetMapping("/get-user-genres")
    public ResponseEntity<?> getUserGenres(@RequestParam(name = "user_id") Long id){
        UserModel userModel = userService.getUserById(id);
        return ResponseEntity.ok(userModel.getGenres());
    }

}
