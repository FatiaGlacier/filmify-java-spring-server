package com.filmify.FilmiFy.User;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        //System.out.println(user);
        Optional<User> foundedOptionalUser;

        foundedOptionalUser = userRepository
                .findOptionalUserByName(user.getUser_name());

        if(foundedOptionalUser.isPresent()){
            throw new IllegalStateException("Name already exists");
        }

        foundedOptionalUser = userRepository
                .findUserByEmail(user.getUser_email());

        if(foundedOptionalUser.isPresent()){
            throw new IllegalStateException("Email already exists");
        }

        userRepository.save(user);
    }

    public User login(String user_name, String password){

        User user = userRepository.findUserByName(user_name);
        if(user == null){
            throw new EntityNotFoundException("User not found");
        }

        if(!(user.getPassword().equals(password))){
            throw new RuntimeException("Wrong password");//must be changed
        }

        return user;
    }
}
