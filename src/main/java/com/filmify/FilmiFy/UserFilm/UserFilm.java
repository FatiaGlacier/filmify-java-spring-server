package com.filmify.FilmiFy.UserFilm;

import com.filmify.FilmiFy.Film.Film;
import com.filmify.FilmiFy.User.User;
import jakarta.persistence.*;

@Entity
@Table(name = "userFilm")
public class UserFilm {
    @Id
    @SequenceGenerator(
            name = "userFilm_sequence",
            sequenceName = "userFilm_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "userFilm_sequence"
    )
    @Column(name = "user_film_id")
    private Long user_film_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    public UserFilm(){

    }

    public UserFilm(Long user_film_id, User user, Film film) {
        this.user_film_id = user_film_id;
        this.user = user;
        this.film = film;
    }

    public UserFilm(User user, Film film) {
        this.user = user;
        this.film = film;
    }

    public Long getUser_film_id() {
        return user_film_id;
    }

    public void setUser_film_id(Long user_film_id) {
        this.user_film_id = user_film_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public String toString() {
        return "UserFilm{" +
                "user_film_id=" + user_film_id +
                ", user_id=" + user.getUser_id() +
                ", film_id=" + film.getFilm_id() +
                '}';
    }
}
