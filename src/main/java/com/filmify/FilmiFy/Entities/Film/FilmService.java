package com.filmify.FilmiFy.Entities.Film;

import com.filmify.FilmiFy.Entities.Genre.Genre;
import com.filmify.FilmiFy.Entities.User.User;
import com.filmify.FilmiFy.Entities.User.UserRepository;
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

    @Autowired
    public FilmService(FilmRepository filmRepository, UserRepository userRepository){
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
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
        List<Film> films = filmRepository.findAll();
        for(Film film: films){
            filmModels.add(FilmModel.toModel(film));
        }

        return filmModels;
    }
}
