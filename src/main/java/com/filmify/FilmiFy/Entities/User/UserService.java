package com.filmify.FilmiFy.Entities.User;

import com.filmify.FilmiFy.Entities.Film.Film;
import com.filmify.FilmiFy.Entities.Film.FilmRepository;
import com.filmify.FilmiFy.Entities.Genre.GenreRepository;
import com.filmify.FilmiFy.Entities.Genre.Genre;
import com.filmify.FilmiFy.Exceptions.*;
import com.filmify.FilmiFy.Models.UserModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    private final UserRepository userRepository;

    private final GenreRepository genreRepository;
    private final FilmRepository filmRepository;

    @Autowired
    public UserService(UserRepository userRepository, GenreRepository genreRepository, FilmRepository filmRepository){
        this.userRepository = userRepository;
        this.genreRepository = genreRepository;
        this.filmRepository = filmRepository;
    }

    public List<UserModel> getAllUsers() {
        List<User> users = userRepository.findAll();

        List<UserModel> userModels = new ArrayList<>();
        for(User user: users){
            userModels.add(UserModel.toModel(user));
        }
        return userModels;
    }

    public UserModel addNewUser(User user) {
        //System.out.println(user);
        Optional<User> foundedOptionalUser;

//        foundedOptionalUser = userRepository
//                .findOptionalUserByName(user.getUser_name());
//
//        if(foundedOptionalUser.isPresent()){
//            throw new UserAlreadyExistsException("Name already used:");
//        }

        foundedOptionalUser = userRepository.findUserByEmail(user.getUser_email());

        if(foundedOptionalUser.isPresent()){
            throw new UserAlreadyExistsException("Email already used: " + user.getUser_email());
        }

        userRepository.save(user);
        user.setGenres(new ArrayList<>());
        return  UserModel.toModel(user);
    }

    /*
    * змінити нік на пошту
    * */
    public UserModel login(String email, String password){

        Optional<User> foundedUser = userRepository.findUserByEmail(email);
        if(foundedUser.isEmpty()){
            throw new UserNotFoundException("User not found, wrong email: " + email);
        }
        User user = foundedUser.get();
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
        if (foundedUser.isEmpty()) {
            throw new UserNotFoundException("User not found, wrong ID: " + id);
        }
        return UserModel.toModel(foundedUser.get());
    }
    @Transactional
    public UserModel addLikedFilm(Long user_id, Long film_id) {
        Optional<User> foundedUser = userRepository.findById(user_id);
        if(foundedUser.isEmpty()){
            throw new UserNotFoundException("User not found, wrong ID: " + user_id);
        }
        User user = foundedUser.get();

        Optional<Film> foundedFilm = filmRepository.findById(film_id);
        if(foundedFilm.isEmpty()){
            throw new FilmNotFoundException("Film not found, wrong ID: " + film_id);
        }
        Film film = foundedFilm.get();

        user.getFilms().add(film);
        userRepository.save(user);

        return UserModel.toModel(user);
    }
    @Transactional
    public UserModel deleteLikedFilm(Long user_id, Long film_id) {
        Optional<User> foundedUser = userRepository.findById(user_id);
        if(foundedUser.isEmpty()){
            throw new UserNotFoundException("User not found, wrong ID: " + user_id);
        }
        User user = foundedUser.get();

        Optional<Film> foundedFilm = filmRepository.findById(film_id);
        if(foundedFilm.isEmpty()){
            throw new FilmNotFoundException("Film not found, wrong ID: " + film_id);
        }
        Film film = foundedFilm.get();

        user.getFilms().remove(film);
        userRepository.save(user);

        return UserModel.toModel(user);
    }

    /**
     *
     * Зробити зміну параметрів по запитам з БД
     *
     */

//    public UserModel changeUserInfo(User user, Long id) {
//        Optional<User> foundedUser = userRepository.findById(id);
//        if (foundedUser.isEmpty()) {
//            throw new UserNotFoundException("User not found, wrong email");
//        }
//        User changedUser = foundedUser.get();
//
//        if (!changedUser.getUser_name().equals(user.getUser_name())) {
//            changedUser.setUser_name(user.getUser_name());
//        }
//
//        if (!changedUser.getUser_email().equals(user.getUser_email())) {
//            changedUser.setUser_email(user.getUser_email());
//        }
//
//        if (!changedUser.getPassword().equals(user.getPassword())) {
//            changedUser.setPassword(user.getPassword());
//        }
//
//        if (!changedUser.getGender().equals(user.getGender())) {
//            changedUser.setGender(user.getGender());
//        }
//
//        if (!changedUser.getBirthday().equals(user.getBirthday())) {
//            changedUser.setBirthday(user.getBirthday());
//        }
//
//        if (!changedUser.getIs_admin().equals(user.getIs_admin())) {
//            changedUser.setIs_admin(user.getIs_admin());
//        }
//
//        changedUser.setGenres(user.getGenres());
//
//
//        userRepository.save(changedUser);
//
//        return UserModel.toModel(changedUser);
//    }
}
