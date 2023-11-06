package com.filmify.FilmiFy.UserFavoriteGenre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFavoriteGenreService {

    private final UserFavoriteGenreRepository userFavoriteGenreRepository;

    @Autowired
    public UserFavoriteGenreService(UserFavoriteGenreRepository userFavoriteGenreRepository){
        this.userFavoriteGenreRepository = userFavoriteGenreRepository;
    }
}
