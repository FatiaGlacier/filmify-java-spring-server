package com.filmify.FilmiFy.UserFilm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFilmService {

    private final UserFilmRepository userFilmRepository;

    @Autowired
    public UserFilmService(UserFilmRepository userFilmRepository){
        this.userFilmRepository = userFilmRepository;
    }

}
