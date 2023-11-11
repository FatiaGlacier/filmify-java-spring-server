package com.filmify.FilmiFy.Entities.UserUploading;

import com.filmify.FilmiFy.Entities.User.User;
import com.filmify.FilmiFy.Entities.Film.Film;
import jakarta.persistence.*;

@Entity
@Table(name = "user_uploading")
public class UserUploading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_uploading_id")
    private Long user_uploading_id;

    @ManyToOne
    @JoinColumn(name = "uu_user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "uu_film_id")
    private Film film;

    @Column(name = "is_permitted")
    private boolean is_permitted;

    public UserUploading() {

    }

    public UserUploading(Long user_uploading_id, User user, Film film, boolean is_permitted) {
        this.user_uploading_id = user_uploading_id;
        this.user = user;
        this.film = film;
        this.is_permitted = is_permitted;
    }

    public UserUploading(User user, Film film, boolean is_permitted) {
        this.user = user;
        this.film = film;
        this.is_permitted = is_permitted;
    }

    public Long getUser_uploading_id() {
        return user_uploading_id;
    }

    public void setUser_uploading_id(Long user_uploading_id) {
        this.user_uploading_id = user_uploading_id;
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

    public boolean isIs_permitted() {
        return is_permitted;
    }

    public void setIs_permitted(boolean is_permitted) {
        this.is_permitted = is_permitted;
    }

    @Override
    public String toString() {
        return "UserUploading{" +
                "user_uploading_id=" + user_uploading_id +
                ", user_id=" + user.getUser_id() +
                ", film_id=" + film.getFilm_id() +
                ", isPermitted=" + is_permitted +
                '}';
    }
}
