package com.filmify.FilmiFy.Entities.Film;

import com.filmify.FilmiFy.Entities.Genre.Genre;
import com.filmify.FilmiFy.Entities.Genre.GenreRepository;
import com.filmify.FilmiFy.Entities.User.User;
import com.filmify.FilmiFy.Entities.User.UserRepository;
import com.filmify.FilmiFy.Exceptions.FilmAlreadyExistException;
import com.filmify.FilmiFy.Exceptions.GenreNotFoundException;
import com.filmify.FilmiFy.Exceptions.UserNotFoundException;
import com.filmify.FilmiFy.Models.FilmModel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

    private FilmRepository filmRepository;

    private UserRepository userRepository;

    private GenreRepository genreRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository, UserRepository userRepository, GenreRepository genreRepository){
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
        this.genreRepository = genreRepository;
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
}
