package com.filmify.FilmiFy.Genre;

import com.filmify.FilmiFy.FilmGenre.FilmGenre;
import com.filmify.FilmiFy.RoomFilm.RoomFilm;
import com.filmify.FilmiFy.UserFavoriteGenre.UserFavoriteGenre;
import com.filmify.FilmiFy.UserUploading.UserUploading;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @SequenceGenerator(
            name = "genre_sequence",
            sequenceName = "genre_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "genre_sequence"
    )
    @Column(name = "genre_id")
    private Long genre_id;

    @Column(name = "genre_name")
    private String genre_name;


    @OneToMany(mappedBy = "genre")
    private List<UserFavoriteGenre> userFavoriteGenres;

    @OneToMany(mappedBy = "genre")
    private List<FilmGenre> filmGenres;


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

    @Override
    public String toString() {
        return "Genre{" +
                "genre_id=" + genre_id +
                ", genre_name='" + genre_name + '\'' +
                '}';
    }
}
