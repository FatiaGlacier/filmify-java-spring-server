package com.filmify.FilmiFy.Entities.UserFavoriteGenre;

import com.filmify.FilmiFy.Entities.User.User;
import com.filmify.FilmiFy.Entities.Genre.Genre;
import jakarta.persistence.*;

@Entity
@Table(name = "userfavoritegenre")
public class UserFavoriteGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_favorite_genre_id")
    private Long user_favorite_genre_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "genre_id")
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

    @Override
    public String toString() {
        return "UserFavoriteGenre{" +
                "user_favorite_genre_id=" + user_favorite_genre_id +
                ", user_id=" + user.getUser_id() +
                ", genre_id=" + genre.getGenre_id() +
                '}';
    }
}
