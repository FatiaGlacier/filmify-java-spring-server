package com.filmify.FilmiFy.Entities.Genre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query("SELECT g FROM Genre g WHERE g.genre_name = :name")
    Optional<Genre> findOptionalGenreByName(@Param("name") String name);
}
