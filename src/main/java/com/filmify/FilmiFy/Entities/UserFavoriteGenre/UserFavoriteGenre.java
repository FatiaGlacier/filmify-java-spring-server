package com.filmify.FilmiFy.Entities.UserFavoriteGenre;

import com.filmify.FilmiFy.Entities.User.User;
import com.filmify.FilmiFy.Entities.Genre.Genre;
import jakarta.persistence.*;

@Entity
@Table(name = "user_favorite_genre")
public class UserFavoriteGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_favorite_genre_id")
    private Long user_favorite_genre_id;

    @Column(name = "ufg_genre_id")
    private Long ufg_genre_id;

    @Column(name = "ufg_user_id")
    private Long ufg_user_id;

    @ManyToOne
    @JoinColumn(name = "ufg_user_id", insertable=false, updatable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "ufg_genre_id", insertable=false, updatable=false)
    private Genre genre;

    public UserFavoriteGenre(){

    }

    public UserFavoriteGenre(Long user_favorite_genre_id, User user, Genre genre) {
        this.user_favorite_genre_id = user_favorite_genre_id;
        this.user = user;
        this.genre = genre;
    }

    public UserFavoriteGenre(User user, Genre genre) {
        this.user = user;
        this.genre = genre;
    }

    public Long getUser_favorite_genre_id() {
        return user_favorite_genre_id;
    }

    public void setUser_favorite_genre_id(Long user_favorite_genre_id) {
        this.user_favorite_genre_id = user_favorite_genre_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Long getUfg_genre_id() {
        return ufg_genre_id;
    }

    public void setUfg_genre_id(Long ufg_genre_id) {
        this.ufg_genre_id = ufg_genre_id;
    }

    public Long getUfg_user_id() {
        return ufg_user_id;
    }

    public void setUfg_user_id(Long ufg_user_id) {
        this.ufg_user_id = ufg_user_id;
    }

    @Override
    public String toString() {
        return "UserFavoriteGenre{" +
                "user_favorite_genre_id=" + user_favorite_genre_id +
                ", user_id=" + user.getUser_id() +
                ", genre_id=" + genre.getGenre_id() +
                '}';
    }
}
