package com.filmify.FilmiFy.Entities.UserFavoriteGenre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFavoriteGenreService {

    private final UserFavoriteGenreRepository userFavoriteGenreRepository;

    @Autowired
    public UserFavoriteGenreService(UserFavoriteGenreRepository userFavoriteGenreRepository){
        this.userFavoriteGenreRepository = userFavoriteGenreRepository;
    }

//    public void addUserFavGenre(Long user_id, List<Genre> genres) {
//        for(Genre genre: genres){
//            UserFavoriteGenre userFavoriteGenre = new UserFavoriteGenre();
//            userFavoriteGenre.setUser();
//        }
//    }
}
