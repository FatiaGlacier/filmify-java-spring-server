package com.filmify.FilmiFy.Entities.Film;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query("SELECT f FROM Film f WHERE f.film_id IN " +
            "(SELECT fg.film.film_id FROM FilmGenre fg WHERE fg.genre.genre_id IN " +
            "(SELECT ufg.genre.genre_id FROM UserFavoriteGenre ufg WHERE ufg.user.user_id= :id))")
    List<Film> getFilmForUser(@Param("id") Long id);

    @Query("SELECT f FROM Film f " +
            "WHERE f.film_name = :film_name " +
            "AND f.film_link = :film_link " +
            "AND f.film_year = :film_year " +
            "AND f.film_duration_minutes = :film_duration_minutes " +
            "AND f.film_imdb_rating = :film_imdb_rating")
    Optional<Film> findByParams(@Param("film_name") String film_name,
                                @Param("film_link") String film_link,
                                @Param("film_year") int film_year,
                                @Param("film_duration_minutes") int film_duration_minutes,
                                @Param("film_imdb_rating") float film_imdb_rating);

//    @Query("SELECT f FROM Film f WHERE f.film_name LIKE :film_name%")
//    Optional<Set<Film>> findByName(@Param("film_name") String name);

    @Query("SELECT f FROM Film f WHERE f.film_name LIKE :film_name")
    Optional<Film> findByName(@Param("film_name") String name);
}
