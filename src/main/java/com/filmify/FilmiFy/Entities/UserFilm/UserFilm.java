package com.filmify.FilmiFy.Entities.UserFilm;

import com.filmify.FilmiFy.Entities.User.User;
import com.filmify.FilmiFy.Entities.Film.Film;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "user_film")
public class UserFilm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_film_id")
    private Long user_film_id;

//    @Column(name = "uf_user_id")
//    private Long uf_user_id;
//
//    @Column(name = "uf_film_id")
//    private Long uf_film_id;

    @ManyToOne
    @JoinColumn(name = "uf_user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "uf_film_id")
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

//    public Long getUf_user_id() {
//        return uf_user_id;
//    }
//
//    public void setUf_user_id(Long uf_user_id) {
//        this.uf_user_id = uf_user_id;
//    }
//
//    public Long getUf_film_id() {
//        return uf_film_id;
//    }
//
//    public void setUf_film_id(Long uf_film_id) {
//        this.uf_film_id = uf_film_id;
//    }

    @Override
    public String toString() {
        return "UserFilm{" +
                "user_film_id=" + user_film_id +
                ", user_id=" + user.getUser_id() +
                ", film_id=" + film.getFilm_id() +
                '}';
    }
}
