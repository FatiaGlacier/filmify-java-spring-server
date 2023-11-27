package com.filmify.FilmiFy.Models;

import com.filmify.FilmiFy.Entities.Film.Film;
import com.filmify.FilmiFy.Entities.FilmGenre.FilmGenre;
import com.filmify.FilmiFy.Entities.Genre.Genre;
import com.filmify.FilmiFy.Entities.RoomFilm.RoomFilm;
import com.filmify.FilmiFy.Entities.UserUploading.UserUploading;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class FilmModel {

    private Long film_id;

    private String film_name;

    private String film_link;

    private String film_desc;

    private int film_year;

    private int film_duration_minutes;

    private float film_IMDb_rating;

    private String image;

    private List<RoomFilm> roomFilms;

    private List<UserUploading> userUploadings;

    private List<FilmGenre> filmGenres;

    private List<GenreModel> genres;

    public FilmModel() {
        this.genres = new ArrayList<>();

    }

    public FilmModel(Long film_id, String film_name, String film_link,
                     String film_desc, int film_year, int film_duration_minutes,
                     float film_IMDb_rating, String image) {
        this.film_id = film_id;
        this.film_name = film_name;
        this.film_link = film_link;
        this.film_desc = film_desc;
        this.film_year = film_year;
        this.film_duration_minutes = film_duration_minutes;
        this.film_IMDb_rating = film_IMDb_rating;
        this.image = image;
    }

    public FilmModel(String film_name, String film_link, String film_desc,
                     int film_year, int film_duration_minutes, float film_IMDb_rating, String image) {
        this.film_name = film_name;
        this.film_link = film_link;
        this.film_desc = film_desc;
        this.film_year = film_year;
        this.film_duration_minutes = film_duration_minutes;
        this.film_IMDb_rating = film_IMDb_rating;
        this.image = image;
    }

    public Long getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Long film_id) {
        this.film_id = film_id;
    }

    public String getFilm_name() {
        return film_name;
    }

    public void setFilm_name(String film_name) {
        this.film_name = film_name;
    }

    public String getFilm_link() {
        return film_link;
    }

    public void setFilm_link(String film_link) {
        this.film_link = film_link;
    }

    public String getFilm_desc() {
        return film_desc;
    }

    public void setFilm_desc(String film_desc) {
        this.film_desc = film_desc;
    }

    public int getFilm_year() {
        return film_year;
    }

    public void setFilm_year(int film_year) {
        this.film_year = film_year;
    }

    public int getFilm_duration_minutes() {
        return film_duration_minutes;
    }

    public void setFilm_duration_minutes(int film_duration_minutes) {
        this.film_duration_minutes = film_duration_minutes;
    }

    public float getFilm_IMDb_rating() {
        return film_IMDb_rating;
    }

    public void setFilm_IMDb_rating(float film_IMDb_rating) {
        this.film_IMDb_rating = film_IMDb_rating;
    }

    public List<GenreModel> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreModel> genres) {
        this.genres = genres;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "FilmModel{" +
                "film_id=" + film_id +
                ", film_name='" + film_name + '\'' +
                ", film_link='" + film_link + '\'' +
                ", film_desc='" + film_desc + '\'' +
                ", film_year=" + film_year +
                ", film_duration_minutes=" + film_duration_minutes +
                ", film_IMDb_rating=" + film_IMDb_rating +
                ", image=" + image +
                ", genres=" + genres +
                '}';
    }

    public static FilmModel toModel(Film film){
        FilmModel model = new FilmModel();

        model.setFilm_id(film.getFilm_id());
        model.setFilm_name(film.getFilm_name());
        model.setFilm_link(film.getFilm_link());
        model.setFilm_desc(film.getFilm_desc());
        model.setFilm_year(film.getFilm_year());
        model.setFilm_duration_minutes(film.getFilm_duration_minutes());
        model.setFilm_IMDb_rating(film.getFilm_IMDb_rating());
        String encoded = Base64.getEncoder().encodeToString(film.getImage());
        model.setImage(encoded);
        for(Genre genre: film.getGenres()){
            model.getGenres().add(GenreModel.toModel(genre));
        }

        return model;
    }
}
