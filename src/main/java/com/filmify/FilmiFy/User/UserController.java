package com.filmify.FilmiFy.User;

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
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/add-user")
    public void registerNewUser(@RequestBody User user){
        userService.addNewUser(user);
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam(name = "user_name") String user_name, @RequestParam(name = "password") String password){
        try {
            User user = userService.login(user_name, password);
            return ResponseEntity.ok(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

}
