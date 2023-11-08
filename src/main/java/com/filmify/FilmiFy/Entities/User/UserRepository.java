package com.filmify.FilmiFy.Entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.user_name = :name")
    Optional<User> findOptionalUserByName(@Param("name") String name);

    @Query("SELECT u FROM User u WHERE u.user_name = :name")
    User findUserByName(@Param("name") String name);

    @Query("SELECT u FROM User u WHERE u.user_email = :email")
    Optional<User> findUserByEmail( @Param("email") String email);

}
