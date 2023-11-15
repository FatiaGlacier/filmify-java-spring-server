package com.filmify.FilmiFy.Entities.User;

import com.filmify.FilmiFy.Entities.Genre.GenreRepository;
import com.filmify.FilmiFy.Entities.Genre.Genre;
import com.filmify.FilmiFy.Exceptions.GenreNotFoundException;
import com.filmify.FilmiFy.Exceptions.UserAlreadyExistsException;
import com.filmify.FilmiFy.Exceptions.UserNotFoundException;
import com.filmify.FilmiFy.Exceptions.WrongPasswordException;
import com.filmify.FilmiFy.Models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final GenreRepository genreRepository;

    @Autowired
    public UserService(UserRepository userRepository, GenreRepository genreRepository){
        this.userRepository = userRepository;
        this.genreRepository = genreRepository;
    }

    public List<UserModel> getAllUsers() {
        List<User> users = userRepository.findAll();

        List<UserModel> userModels = new ArrayList<>();
        for(User user: users){
            userModels.add(UserModel.toModel(user));
        }
        return userModels;
    }

    public void addNewUser(User user) {
        //System.out.println(user);
        Optional<User> foundedOptionalUser;

//        foundedOptionalUser = userRepository
//                .findOptionalUserByName(user.getUser_name());
//
//        if(foundedOptionalUser.isPresent()){
//            throw new UserAlreadyExistsException("Name already used:");
//        }

        foundedOptionalUser = userRepository
                .findUserByEmail(user.getUser_email());

        if(foundedOptionalUser.isPresent()){
            throw new UserAlreadyExistsException("Email already used: " + user.getUser_email());
        }

        userRepository.save(user);
    }

    /*
    * змінити нік на пошту
    * */
    public UserModel login(String user_name, String password){

        User user = userRepository.findUserByName(user_name);
        if(user == null){
            throw new UserNotFoundException("User not found, wrong name: " + user_name);
        }

        if(!(user.getPassword().equals(password))){
            throw new WrongPasswordException("Wrong password");
        }

        return UserModel.toModel(user);
    }

    public void addUserFavoriteGenres(List<Genre> genres, Long user_id) {

        Optional<User> foundedUser = userRepository.findById(user_id);
        if(foundedUser.isEmpty()){
            throw new UserNotFoundException("User not found, wrong ID: " + user_id);
        }
        User user = foundedUser.get();
        for(Genre genre: genres){
            Optional<Genre> foundedGenre = genreRepository.findOptionalGenreByName(genre.getGenre_name());
            if(foundedGenre.isEmpty()){
                throw new GenreNotFoundException("Genre not found, wrong Name");
            }
            user.getGenres().add(foundedGenre.get());
        }
        userRepository.save(user);
    }

    public UserModel getUserById(Long id) {
        Optional<User> foundedUser = userRepository.findById(id);
        if(foundedUser.isEmpty()){
            throw new UserNotFoundException("User not found, wrong ID: " + id);
        }
        return UserModel.toModel(foundedUser.get());
    }
}
