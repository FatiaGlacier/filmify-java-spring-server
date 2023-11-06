package com.filmify.FilmiFy.UserFavoriteGenre;

import com.filmify.FilmiFy.Film.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFavoriteGenreRepository extends JpaRepository<UserFavoriteGenre, Long> {
}
