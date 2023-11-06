package com.filmify.FilmiFy.UserFilm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/user-film")
public class UserFilmController {

    private final UserFilmService userFilmService;

    @Autowired
    public UserFilmController(UserFilmService userFilmService){
        this.userFilmService = userFilmService;
    }

}
