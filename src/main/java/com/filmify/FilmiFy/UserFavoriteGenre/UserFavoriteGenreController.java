package com.filmify.FilmiFy.UserFavoriteGenre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/user-favorite-genre")
public class UserFavoriteGenreController {

    private final UserFavoriteGenreService userFavoriteGenreService;

    @Autowired
    public UserFavoriteGenreController(UserFavoriteGenreService userFavoriteGenreService){
        this.userFavoriteGenreService = userFavoriteGenreService;
    }
}
