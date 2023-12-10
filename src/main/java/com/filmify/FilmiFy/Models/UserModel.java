package com.filmify.FilmiFy.Models;

import com.filmify.FilmiFy.Entities.Film.Film;
import com.filmify.FilmiFy.Entities.Genre.Genre;
import com.filmify.FilmiFy.Entities.User.User;
import com.filmify.FilmiFy.Entities.UserFilm.UserFilm;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.filmify.FilmiFy.Entities.Film.Film;
import com.filmify.FilmiFy.Entities.Genre.Genre;
import com.filmify.FilmiFy.Entities.User.User;
import com.filmify.FilmiFy.Entities.UserFavoriteGenre.UserFavoriteGenre;
import com.filmify.FilmiFy.Entities.UserFilm.UserFilm;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserModel {

    private Long user_id;

    private String user_name;

    private String user_email;

    private String password;

    private String gender;

    private LocalDate birthday;

    private LocalDate registration_date;

    private Boolean is_admin;

//    private List<UserRoom> userRooms;
//
//    private List<UserFilm> userFilms;
//
//    private List<UserUploading> userUploadings;
//
//    private List<UserFavoriteGenre> userFavoriteGenres;
//
//    private List<RoomFilm> roomFilms;

    private List<GenreModel> genres;


    private List<FilmModel> films;

    public UserModel(){
        this.genres = new ArrayList<>();
        this.films = new ArrayList<>();
    }

    //For login
    public UserModel(String user_name, String password) {
        this.user_name = user_name;
        this.password = password;
    }

    public UserModel(Long user_id, String user_name, String user_email,
                     String password, String gender, LocalDate birthday,
                     LocalDate registration_date, boolean is_admin) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
        this.registration_date = registration_date;
        this.is_admin = is_admin;
        this.genres = new ArrayList<>();
        this.films = new ArrayList<>();
    }

    public UserModel(Long user_id, String user_name, String user_email,
                     String password, String gender, LocalDate birthday,
                     LocalDate registration_date, boolean is_admin, List<GenreModel> genres) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
        this.registration_date = registration_date;
        this.is_admin = is_admin;
        this.genres = genres;
        this.films = new ArrayList<>();
    }

    public UserModel(String user_name, String user_email, String password,
                     String gender, LocalDate birthday, LocalDate registration_date,
                     boolean is_admin) {
        this.user_name = user_name;
        this.user_email = user_email;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
        this.registration_date = registration_date;
        this.is_admin = is_admin;
        this.genres = new ArrayList<>();
        this.films = new ArrayList<>();
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(LocalDate registration_date) {
        this.registration_date = registration_date;
    }

    //@JsonProperty("is_admin")
    public Boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }

    public List<FilmModel> getFilms() {
        return films;
    }

    public void setFilms(List<FilmModel> films) {
        this.films = films;
    }

    public List<GenreModel> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreModel> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_email='" + user_email + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", registration_date=" + registration_date +
                ", is_admin=" + is_admin +
                '}';
    }

    public static UserModel toModel(User user){
        UserModel model = new UserModel();
        model.setUser_id(user.getUser_id());
        model.setUser_name(user.getUser_name());
        model.setUser_email(user.getUser_email());
        model.setPassword(user.getPassword());
        model.setGender(user.getGender());
        model.setBirthday(user.getBirthday());
        model.setRegistration_date(user.getRegistration_date());
        model.setIs_admin(user.getIs_admin());
        for(UserFavoriteGenre userFavoriteGenre: user.getUserFavoriteGenres()){
            model.getGenres().add(GenreModel.toModel(userFavoriteGenre.getGenre()));
        }
        if(user.getUserFilms() != null){
            for(UserFilm userFilm : user.getUserFilms()){
                model.getFilms().add(FilmModel.toModel(userFilm.getFilm()));
            }
        }else{
            user.setFilms(new ArrayList<>());
        }

        return model;
    }
}
