package com.filmify.FilmiFy.Entities.Film;

import com.filmify.FilmiFy.Entities.Genre.Genre;
import com.filmify.FilmiFy.Entities.Genre.GenreRepository;
import com.filmify.FilmiFy.Entities.Room.Room;
import com.filmify.FilmiFy.Entities.Room.RoomRepository;
import com.filmify.FilmiFy.Entities.User.User;
import com.filmify.FilmiFy.Entities.User.UserRepository;
import com.filmify.FilmiFy.Exceptions.*;
import com.filmify.FilmiFy.Models.FilmModel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilmService {

    private FilmRepository filmRepository;

    private UserRepository userRepository;

    private GenreRepository genreRepository;

    private RoomRepository roomRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository,
                       UserRepository userRepository,
                       GenreRepository genreRepository,
                       RoomRepository roomRepository){
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
        this.genreRepository = genreRepository;
        this.roomRepository = roomRepository;
    }

    public List<FilmModel> getAll() {
        List<FilmModel> filmModels = new ArrayList<>();
        List<Film> films = filmRepository.findAll();
        for(Film film: films){
            filmModels.add(FilmModel.toModel(film));
        }

        return filmModels;
    }

    public List<FilmModel> getFilmForUser(Long user_id) {
        Optional<User> foundedUser = userRepository.findById(user_id);
        if(foundedUser.isEmpty()){
            throw new UserNotFoundException("User not found, wrong ID: " + user_id);
        }
        User user = foundedUser.get();
        List<FilmModel> filmModels = new ArrayList<>();
        List<Film> films = filmRepository.getFilmForUser(user_id);
        for(Film film: films){
            filmModels.add(FilmModel.toModel(film));
        }

        return filmModels;
    }

    public void addFilm(Film film, List<Long> genreIds) {
        Optional<Film> foundedFilm = filmRepository.findByParams(film.getFilm_name(),
                film.getFilm_link(), film.getFilm_year(), film.getFilm_duration_minutes(),
                film.getFilm_IMDb_rating());

        if(foundedFilm.isPresent()){
            throw new FilmAlreadyExistException("Film already exists");
        }
        film.setGenres(new ArrayList<>());
        for(Long id: genreIds){
            Optional<Genre> genre = genreRepository.findById(id);
            if(genre.isEmpty()){
                throw new GenreNotFoundException("Genre not found, wrong ID: " + id);
            }
            film.getGenres().add(genre.get());
        }

        filmRepository.save(film);
    }

//    public Set<FilmModel> findFilmByName(String name) {
//        Optional<Set<Film>> foundedFilms = filmRepository.findByName(name);
//        if(foundedFilms.isEmpty()){
//            throw new FilmNotFoundException("Film not found, wrong name: " + name);
//        }
//
//        Set<FilmModel> filmModels = new HashSet<>();
//        for(Film film : foundedFilms.get()){
//            filmModels.add(FilmModel.toModel(film));
//        }
//
//        return filmModels;
//    }

    public FilmModel findFilmByName(String name) {
        Optional<Film> foundedFilms = filmRepository.findByName(name);
        if(foundedFilms.isEmpty()){
            throw new FilmNotFoundException("Film not found, wrong name: " + name);
        }

        return FilmModel.toModel(foundedFilms.get());
    }

    public List<FilmModel> getFilmForRoom(String code) {
        Optional<Room> foundRoom = roomRepository.findRoomByCode(code);
        if(foundRoom.isEmpty()){
            throw new RoomNotFoundException("Room not found, wrong code: " + code);
        }

        Optional<List<Film>> foundFilms = filmRepository.getFilmsForRoom(code);
        if(foundFilms.isEmpty()){
            return new ArrayList<FilmModel>();
        }

        List<FilmModel> resultFilms = new ArrayList<>();
        for(Film film: foundFilms.get()){
            resultFilms.add(FilmModel.toModel(film));
        }

        return resultFilms;
    }
}
