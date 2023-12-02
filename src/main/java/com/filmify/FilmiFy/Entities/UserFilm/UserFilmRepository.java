package com.filmify.FilmiFy.Entities.UserFilm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserFilmRepository extends JpaRepository<UserFilm, Long> {

    @Query("""
            SELECT uf
            FROM UserFilm uf
            WHERE uf.user.user_id = :user_id AND uf.film.film_id = :film_id
            """)
    Optional<UserFilm> findByUserIdAndFilmId(long user_id, long film_id);

    @Modifying
    @Query("DELETE FROM UserFilm uf WHERE uf.user_film_id = :user_film_id")
    void removeById(long user_film_id);
}
