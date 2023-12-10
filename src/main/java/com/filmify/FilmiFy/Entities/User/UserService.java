package com.filmify.FilmiFy.Entities.User;

import com.filmify.FilmiFy.Entities.Film.Film;
import com.filmify.FilmiFy.Entities.Film.FilmRepository;
import com.filmify.FilmiFy.Entities.Genre.GenreRepository;
import com.filmify.FilmiFy.Entities.Genre.Genre;
import com.filmify.FilmiFy.Entities.UserFavoriteGenre.UserFavoriteGenre;
import com.filmify.FilmiFy.Entities.UserFilm.UserFilm;
import com.filmify.FilmiFy.Entities.UserFilm.UserFilmRepository;
import com.filmify.FilmiFy.Exceptions.*;
import com.filmify.FilmiFy.Models.FilmModel;
import com.filmify.FilmiFy.Models.GenreModel;
import com.filmify.FilmiFy.Models.UserModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    private final UserRepository userRepository;
    private final UserFilmRepository userFilmRepository;
    private final GenreRepository genreRepository;
    private final FilmRepository filmRepository;

//    @Autowired
//    public UserService(UserRepository userRepository, UserFilmRepository userFilmRepository, GenreRepository genreRepository, FilmRepository filmRepository){
//        this.userRepository = userRepository;
//        this.userFilmRepository = userFilmRepository;
//        this.genreRepository = genreRepository;
//        this.filmRepository = filmRepository;
//    }

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

    public UserModel getUserById(Long id) {
        Optional<User> foundedUser = userRepository.findById(id);
        if (foundedUser.isEmpty()) {
            throw new UserNotFoundException("User not found, wrong ID: " + id);
        }
        return UserModel.toModel(foundedUser.get());
    }
    @Transactional
    public UserModel addUserFavoriteGenres(Set<Genre> genres, Long user_id) {
        Set<UserFavoriteGenre> userFavoriteGenres = new HashSet<>();
        Optional<User> foundedUser = userRepository.findById(user_id);
        if(foundedUser.isEmpty()){
            throw new UserNotFoundException("User not found, wrong ID: " + user_id);
        }
        User user = foundedUser.get();
        userRepository.deleteUserFavGenres(user_id);
        for(Genre genre: genres){
            Optional<Genre> foundedGenre = genreRepository.findOptionalGenreByName(genre.getGenre_name());
            if(foundedGenre.isEmpty()){
                throw new GenreNotFoundException("Genre not found, wrong Name");
            }
            UserFavoriteGenre userFavoriteGenre = new UserFavoriteGenre();
            userFavoriteGenre.setUser(user);
            userFavoriteGenre.setGenre(foundedGenre.get());
            if(genre.getUserFavoriteGenres() == null){
                genre.setUserFavoriteGenres(new HashSet<>());
            }
            genre.getUserFavoriteGenres().add(userFavoriteGenre);
            userFavoriteGenres.add(userFavoriteGenre);
        }
        user.setUserFavoriteGenres(userFavoriteGenres);

//        for(UserFavoriteGenre userFavoriteGenre: userFavoriteGenres){
//            userFavoriteGenreRepository.save(userFavoriteGenre);
//        }
        userRepository.save(user);
        return UserModel.toModel(user);
    }

    /*
    TODO: Custom exceptions
      */
    @Transactional
    public UserModel addLikedFilm(Long user_id, Long film_id) {
        Optional<User> foundedUser = userRepository.findById(user_id);
        if(foundedUser.isEmpty()){
            throw new UserNotFoundException("User not found, wrong ID: " + user_id);
        }
        User user = foundedUser.get();
        userRepository.deleteUserFavGenres(user.getUser_id());
        log.info(user.toString());
        Optional<Film> foundedFilm = filmRepository.findById(film_id);
        if(foundedFilm.isEmpty()){
            throw new FilmNotFoundException("Film not found, wrong ID: " + film_id);
        }
        Film film = foundedFilm.get();

        Optional<UserFilm> foundUserFilm = userFilmRepository.findByUserIdAndFilmId(user_id, film_id);
        if(foundedFilm.isPresent()){
            throw new FilmNotFoundException("Film already in user list, film ID: " + film_id);
        }
        UserFilm userFilm = new UserFilm();
        userFilm.setUser(user);
        userFilm.setFilm(film);
        user.addToUserFilms(userFilm);
        film.addToUserFilms(userFilm);

        userFilmRepository.saveAndFlush(userFilm);
        return UserModel.toModel(user);
    }
    @Transactional
    public UserModel deleteLikedFilm(long user_id, long film_id) {
        Optional<User> foundedUser = userRepository.findById(user_id);
        if (foundedUser.isEmpty()) {
            throw new UserNotFoundException("User not found, wrong ID: " + user_id);
        }
        User user = foundedUser.get();

        Optional<Film> foundedFilm = filmRepository.findById(film_id);
        if (foundedFilm.isEmpty()) {
            throw new FilmNotFoundException("Film not found, wrong ID: " + film_id);
        }
        Film film = foundedFilm.get();

        Optional<UserFilm> foundUserFilm = userFilmRepository.findByUserIdAndFilmId(user_id, film_id);
        if (foundUserFilm.isEmpty()) {
            throw new FilmNotFoundException("UserFilm not found, wrong ID: " + film_id);
        }
        UserFilm userFilm = foundUserFilm.get();
        userFilmRepository.removeById(userFilm.getUser_film_id());

        return UserModel.toModel(userFilm.getUser());
    }

    public Set<FilmModel> getUserFilms(Long user_id) {
        Optional<User> foundedUser = userRepository.findById(user_id);
        if (foundedUser.isEmpty()) {
            throw new UserNotFoundException("User not found, wrong ID: " + user_id);
        }
        User user = foundedUser.get();

        Set<FilmModel> resultSet = new HashSet<>();
        for(UserFilm userFilm: user.getUserFilms()){
            resultSet.add(FilmModel.toModel(userFilm.getFilm()));
        }

        return resultSet;
    }
    @Transactional
    public UserModel changeUserInfo(UserModel user, Long id) {
        Optional<User> foundedUser = userRepository.findById(id);
        if (foundedUser.isEmpty()) {
            throw new UserNotFoundException("User not found, wrong email");
        }
        User foundUser = foundedUser.get();

        if (!foundUser.getUser_name().equals(user.getUser_name())) {
            foundUser.setUser_name(user.getUser_name());
        }

//        Optional<User> foundEmailUser = userRepository.findUserByEmail(user.getUser_email());
//        if(foundEmailUser.isPresent()){
//            if(!foundEmailUser.get().getUser_id().equals(id) && foundEmailUser.get().getUser_email().equals(user.getUser_email())){
//                throw new UserAlreadyExistsException("Email already in use");
//            }
//        }
//        if (!foundUser.getUser_email().equals(user.getUser_email())) {
//            foundUser.setUser_email(user.getUser_email());
//        }

        if (!foundUser.getPassword().equals(user.getPassword())) {
            foundUser.setPassword(user.getPassword());
        }

        if (!foundUser.getGender().equals(user.getGender())) {
            foundUser.setGender(user.getGender());
        }

        if (!foundUser.getBirthday().equals(user.getBirthday())) {
            foundUser.setBirthday(user.getBirthday());
        }

        userRepository.deleteUserFavGenres(id);

        Set<UserFavoriteGenre> userFavoriteGenres = new HashSet<>();
        for(GenreModel genreModel: user.getGenres()){
            Optional<Genre> foundedGenre = genreRepository.findOptionalGenreByName(genreModel.getGenre_name());
            if(foundedGenre.isEmpty()){
                throw new GenreNotFoundException("Genre not found, wrong Name");
            }
            Genre genre = foundedGenre.get();
            UserFavoriteGenre userFavoriteGenre = new UserFavoriteGenre();
            userFavoriteGenre.setUser(foundUser);
            userFavoriteGenre.setGenre(genre);
            if(genre.getUserFavoriteGenres() == null){
                genre.setUserFavoriteGenres(new HashSet<>());
            }
            genre.getUserFavoriteGenres().add(userFavoriteGenre);
            userFavoriteGenres.add(userFavoriteGenre);
        }
        foundUser.setUserFavoriteGenres(userFavoriteGenres);
        userRepository.saveAndFlush(foundUser);

        return UserModel.toModel(foundUser);
    }

    public Boolean isInUserList(Long user_id, Long film_id) {
        Optional<User> foundedUser = userRepository.findById(user_id);
        if (foundedUser.isEmpty()) {
            throw new UserNotFoundException("User not found, wrong ID: " + user_id);
        }

        Optional<Film> foundedFilm = filmRepository.findById(film_id);
        if (foundedFilm.isEmpty()) {
            throw new FilmNotFoundException("Film not found, wrong ID: " + film_id);
        }

        Optional<UserFilm> foundUserFilm = userFilmRepository.findByUserIdAndFilmId(user_id,film_id);

        if(foundUserFilm.isEmpty()){
            return false;
        }

        return true;
    }
}
