package com.filmify.FilmiFy.Entities.FilmGenre;

import com.filmify.FilmiFy.Entities.Film.Film;
import com.filmify.FilmiFy.Entities.Genre.Genre;
import jakarta.persistence.*;

@Entity
@Table(name = "filmgenre")
public class FilmGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_genre_id")
    private Long film_genre_id;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public FilmGenre(){

    }

    public FilmGenre(Long film_genre_id, Film film, Genre genre) {
        this.film_genre_id = film_genre_id;
        this.film = film;
        this.genre = genre;
    }

    public FilmGenre(Film film, Genre genre) {
        this.film = film;
        this.genre = genre;
    }

    public Long getFilm_genre_id() {
        return film_genre_id;
    }

    public void setFilm_genre_id(Long film_genre_id) {
        this.film_genre_id = film_genre_id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "FilmGenre{" +
                "film_genre_id=" + film_genre_id +
                ", film_id=" + film.getFilm_id() +
                ", genre_id=" + genre.getGenre_id() +
                '}';
    }
}
