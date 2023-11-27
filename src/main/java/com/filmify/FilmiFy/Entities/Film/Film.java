package com.filmify.FilmiFy.Entities.Film;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.filmify.FilmiFy.Entities.FilmGenre.FilmGenre;
import com.filmify.FilmiFy.Entities.Genre.Genre;
import com.filmify.FilmiFy.Entities.RoomFilm.RoomFilm;
import com.filmify.FilmiFy.Entities.User.User;
import com.filmify.FilmiFy.Entities.UserUploading.UserUploading;
import com.filmify.FilmiFy.Models.FilmModel;
import com.filmify.FilmiFy.Models.GenreModel;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Cacheable(false)
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
    @Column(name = "film_imdb_rating")
    private float film_imdb_rating;
    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;

    @OneToMany(mappedBy = "film")
    private List<RoomFilm> roomFilms;

    @OneToMany(mappedBy = "film")
    private List<UserUploading> userUploadings;

    @OneToMany(mappedBy = "film")
    private List<FilmGenre> filmGenres;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(
            name = "user_film",
            joinColumns = @JoinColumn(name = "uf_film_id"),
            inverseJoinColumns = @JoinColumn(name = "uf_user_id")
    )
    private List<User> users;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "film_genre",
            joinColumns = @JoinColumn(name = "fg_film_id"),
            inverseJoinColumns = @JoinColumn(name = "fg_genre_id")
    )
    private List<Genre> genres;

    public Film() {

    }

    public Film(Long film_id, String film_name, String film_link,
                String film_desc, int film_year, int film_duration_minutes,
                float film_imdb_rating, byte[] image) {
        this.film_id = film_id;
        this.film_name = film_name;
        this.film_link = film_link;
        this.film_desc = film_desc;
        this.film_year = film_year;
        this.film_duration_minutes = film_duration_minutes;
        this.film_imdb_rating = film_imdb_rating;
        this.image = image;
    }

    public Film(String film_name, String film_link, String film_desc,
                int film_year, int film_duration_minutes, float film_imdb_rating, byte[] image) {
        this.film_name = film_name;
        this.film_link = film_link;
        this.film_desc = film_desc;
        this.film_year = film_year;
        this.film_duration_minutes = film_duration_minutes;
        this.film_imdb_rating = film_imdb_rating;
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
        return film_imdb_rating;
    }

    public void setFilm_IMDb_rating(float film_IMDb_rating) {
        this.film_imdb_rating = film_IMDb_rating;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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
                ", film_IMDb_rating=" + film_imdb_rating +
                '}';
    }
}
