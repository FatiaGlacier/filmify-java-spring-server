package com.filmify.FilmiFy.UserFilm;

import com.filmify.FilmiFy.Film.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFilmRepository extends JpaRepository<UserFilm, Long> {
}
