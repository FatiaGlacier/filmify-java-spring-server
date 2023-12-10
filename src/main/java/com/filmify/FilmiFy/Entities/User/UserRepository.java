package com.filmify.FilmiFy.Entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.user_name = :name")
    Optional<User> findOptionalUserByName(@Param("name") String name);

    @Query("SELECT u FROM User u WHERE u.user_name = :name")
    Optional<User> findUserByName(@Param("name") String name);

    @Query("SELECT u FROM User u WHERE u.user_email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);
    //    @Query("INSERT INTO UserFilm uf(uf_user_id, uf_film_id) VALUES (:user_id, :film_id)")
//    void saveLikedFilm(Long user_id, Long film_id);
    @Modifying
    @Query("DELETE FROM UserFavoriteGenre ufg WHERE ufg.user.user_id = :user_id")
    void deleteUserFavGenres(@Param("user_id") long user_id);
}

