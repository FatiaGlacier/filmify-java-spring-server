package com.filmify.FilmiFy.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.filmify.FilmiFy.Entities.FilmGenre.FilmGenre;
import com.filmify.FilmiFy.Entities.Genre.Genre;
import com.filmify.FilmiFy.Entities.UserFavoriteGenre.UserFavoriteGenre;
import jakarta.persistence.*;

import java.util.List;


public class GenreModel {

    private Long genre_id;

    private String genre_name;

    private List<UserFavoriteGenre> userFavoriteGenres;

    private List<FilmGenre> filmGenres;

    @JsonIgnore
    private List<UserModel> users;


    public GenreModel(){

    }

    public GenreModel(Long genre_id, String genre_name) {
        this.genre_id = genre_id;
        this.genre_name = genre_name;
    }

    public GenreModel(String genre_name) {
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

    public List<UserModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserModel> users) {
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
