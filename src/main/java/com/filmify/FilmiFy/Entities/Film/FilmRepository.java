package com.filmify.FilmiFy.Entities.Film;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query("SELECT f FROM Film f WHERE f.film_id IN " +
            "(SELECT fg.fg_film_id FROM FilmGenre fg WHERE fg.fg_genre_id IN " +
            "(SELECT ufg.ufg_genre_id FROM UserFavoriteGenre ufg WHERE ufg.ufg_user_id = :id))")
    List<Film> getFilmForUser(@Param("id") Long id);
}
