package com.filmify.FilmiFy.UserUploading;

import com.filmify.FilmiFy.Film.Film;
import com.filmify.FilmiFy.User.User;
import jakarta.persistence.*;

@Entity
@Table(name = "userUploading")
public class UserUploading {
    @Id
    @SequenceGenerator(
            name = "userUploading_sequence",
            sequenceName = "userUploading_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "userUploading_sequence"
    )
    @Column(name = "user_uploading_id")
    private Long user_uploading_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    private boolean isPermitted;

    public UserUploading() {

    }

    public UserUploading(Long user_uploading_id, User user, Film film, boolean isPermitted) {
        this.user_uploading_id = user_uploading_id;
        this.user = user;
        this.film = film;
        this.isPermitted = isPermitted;
    }

    public UserUploading(User user, Film film, boolean isPermitted) {
        this.user = user;
        this.film = film;
        this.isPermitted = isPermitted;
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

    public boolean isPermitted() {
        return isPermitted;
    }

    public void setPermitted(boolean permitted) {
        isPermitted = permitted;
    }

    @Override
    public String toString() {
        return "UserUploading{" +
                "user_uploading_id=" + user_uploading_id +
                ", user_id=" + user.getUser_id() +
                ", film_id=" + film.getFilm_id() +
                ", isPermitted=" + isPermitted +
                '}';
    }
}
