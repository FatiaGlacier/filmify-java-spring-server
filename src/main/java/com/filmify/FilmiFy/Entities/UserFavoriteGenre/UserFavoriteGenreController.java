package com.filmify.FilmiFy.Entities.UserFavoriteGenre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/user-favorite-genre")
public class UserFavoriteGenreController {

    private final UserFavoriteGenreService userFavoriteGenreService;

    @Autowired
    public UserFavoriteGenreController(UserFavoriteGenreService userFavoriteGenreService){
        this.userFavoriteGenreService = userFavoriteGenreService;
    }

//    @PostMapping("add-user-fav-genre")
//    public void addUserFavGenre(@RequestParam(name = "user_id") Long user_id, @RequestBody List<Genre> genres){
//        userFavoriteGenreService.addUserFavGenre(user_id, genres);
//    }
}
