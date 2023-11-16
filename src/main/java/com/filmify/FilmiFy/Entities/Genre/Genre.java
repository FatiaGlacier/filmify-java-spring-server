package com.filmify.FilmiFy.Entities.Genre;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.filmify.FilmiFy.Entities.Film.Film;
import com.filmify.FilmiFy.Entities.FilmGenre.FilmGenre;
import com.filmify.FilmiFy.Entities.User.User;
import com.filmify.FilmiFy.Entities.UserFavoriteGenre.UserFavoriteGenre;
import com.filmify.FilmiFy.Models.GenreModel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long genre_id;

    @Column(name = "genre_name")
    private String genre_name;


    @OneToMany(mappedBy = "genre")
    private List<UserFavoriteGenre> userFavoriteGenres;

    @OneToMany(mappedBy = "genre")
    private List<FilmGenre> filmGenres;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "user_favorite_genre",
            joinColumns = @JoinColumn(name = "ufg_genre_id"),
            inverseJoinColumns = @JoinColumn(name = "ufg_user_id")
    )
    private List<User> users;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "film_genre",
            joinColumns = @JoinColumn(name = "fg_genre_id"),
            inverseJoinColumns = @JoinColumn(name = "fg_film_id")
    )
    private List<Film> films;

    public Genre(){

    }


    public Genre(Long genre_id, String genre_name) {
        this.genre_id = genre_id;
        this.genre_name = genre_name;
    }

    public Genre(String genre_name) {
        this.genre_name = genre_name;
    }

    public Long getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Long genre_id) {
        this.genre_id = genre_id;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genre_id=" + genre_id +
                ", genre_name='" + genre_name + '\'' +
                '}';
    }

    public static GenreModel toModel(Genre genre){
        GenreModel model = new GenreModel();
        model.setGenre_id(genre.getGenre_id());
        model.setGenre_name(genre.getGenre_name());

        return model;
    }
}
