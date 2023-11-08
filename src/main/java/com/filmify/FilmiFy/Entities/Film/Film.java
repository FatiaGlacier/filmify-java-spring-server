package com.filmify.FilmiFy.Entities.Film;

import com.filmify.FilmiFy.Entities.FilmGenre.FilmGenre;
import com.filmify.FilmiFy.Entities.RoomFilm.RoomFilm;
import com.filmify.FilmiFy.Entities.UserUploading.UserUploading;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long film_id;
    @Column(name = "film_name")
    private String film_name;
    @Column(name = "film_link")
    private String film_link;
    @Column(name = "film_desc")
    private String film_desc;
    @Column(name = "film_year")
    private int film_year;
    @Column(name = "film_duration_minutes")
    private int film_duration_minutes;
    @Column(name = "film_IMDb_rating")
    private float film_IMDb_rating;

    @OneToMany(mappedBy = "film")
    private List<RoomFilm> roomFilms;

    @OneToMany(mappedBy = "film")
    private List<UserUploading> userUploadings;

    @OneToMany(mappedBy = "film")
    private List<FilmGenre> filmGenres;

    public Film() {

    }

    public Film(Long film_id, String film_name, String film_link,
                String film_desc, int film_year, int film_duration_minutes,
                float film_IMDb_rating) {
        this.film_id = film_id;
        this.film_name = film_name;
        this.film_link = film_link;
        this.film_desc = film_desc;
        this.film_year = film_year;
        this.film_duration_minutes = film_duration_minutes;
        this.film_IMDb_rating = film_IMDb_rating;
    }

    public Film(String film_name, String film_link, String film_desc,
                int film_year, int film_duration_minutes, float film_IMDb_rating) {
        this.film_name = film_name;
        this.film_link = film_link;
        this.film_desc = film_desc;
        this.film_year = film_year;
        this.film_duration_minutes = film_duration_minutes;
        this.film_IMDb_rating = film_IMDb_rating;
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

    @Override
    public String toString() {
        return "Film{" +
                "film_id=" + film_id +
                ", film_name='" + film_name + '\'' +
                ", film_link='" + film_link + '\'' +
                ", film_desc='" + film_desc + '\'' +
                ", film_year=" + film_year +
                ", film_duration_minutes=" + film_duration_minutes +
                ", film_IMDb_rating=" + film_IMDb_rating +
                '}';
    }
}
