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

    @Query(value = "SELECT * FROM film\n" +
            "WHERE film_id IN (\n" +
            "    SELECT fg_film_id FROM film_genre WHERE fg_genre_id IN (\n" +
            "        SELECT DISTINCT ufg2.ufg_genre_id\n" +
            "        FROM user_favorite_genre ufg1\n" +
            "        INNER JOIN user_favorite_genre ufg2 ON ufg1.ufg_genre_id = ufg2.ufg_genre_id\n" +
            "        WHERE ufg2.ufg_user_id IN (\n" +
            "            SELECT ur_user_id\n" +
            "            FROM user_room\n" +
            "            WHERE ur_room_id IN (\n" +
            "                SELECT room_id\n" +
            "                FROM room\n" +
            "                WHERE room_code = :code\n" +
            "            )\n" +
            "        ) AND ufg2.ufg_user_id != ufg1.ufg_user_id\n" +
            "    )\n" +
            ")",
    nativeQuery = true)
    Optional<List<Film>> getFilmsForRoom(String code);
}
