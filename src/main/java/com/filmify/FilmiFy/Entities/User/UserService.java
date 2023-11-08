package com.filmify.FilmiFy.Entities.User;

import com.filmify.FilmiFy.Entities.Genre.GenreRepository;
import com.filmify.FilmiFy.Entities.Genre.Genre;
import com.filmify.FilmiFy.Models.UserModel;
import jakarta.persistence.EntityNotFoundException;
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

    public UserModel login(String user_name, String password){

        User user = userRepository.findUserByName(user_name);
        if(user == null){
            throw new EntityNotFoundException("User not found");
        }

        if(!(user.getPassword().equals(password))){
            throw new RuntimeException("Wrong password");//must be changed
        }

        return UserModel.toModel(user);
    }

    public void addUserFavoriteGenres(List<Genre> genres, Long user_id) {

        Optional<User> foundedUser = userRepository.findById(user_id);
        if(!(foundedUser.isPresent())){
            throw new EntityNotFoundException("User not founded");
        }
        User user = foundedUser.get();
        for(Genre genre: genres){
            Optional<Genre> foundedGenre = genreRepository.findOptionalGenreByName(genre.getGenre_name());
            if(!(foundedGenre.isPresent())){
                throw new EntityNotFoundException("Genre not founded");
            }
            user.getGenres().add(foundedGenre.get());
        }
        userRepository.save(user);
    }

    public UserModel getUserById(Long id) {
        return UserModel.toModel(userRepository.findById(id).get());
    }
}
