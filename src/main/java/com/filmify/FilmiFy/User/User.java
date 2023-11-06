package com.filmify.FilmiFy.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.filmify.FilmiFy.RoomFilm.RoomFilm;
import com.filmify.FilmiFy.UserFavoriteGenre.UserFavoriteGenre;
import com.filmify.FilmiFy.UserFilm.UserFilm;
import com.filmify.FilmiFy.UserRoom.UserRoom;
import com.filmify.FilmiFy.UserUploading.UserUploading;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnTransformer;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "user_email")
    private String user_email;

    @Column(name = "password")
    private String password;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "registration_date")
    private LocalDate registration_date;

    @Column(name = "is_admin")
    @ColumnTransformer(read = "case when is_admin = 1 then true else false end", write = "case when ? then 1 else 0 end")
    private Boolean is_admin;

    @OneToMany(mappedBy = "user")
    private List<UserRoom> userRooms;

    @OneToMany(mappedBy = "user")
    private List<UserFilm> userFilms;

    @OneToMany(mappedBy = "user")
    private List<UserUploading> userUploadings;

    @OneToMany(mappedBy = "user")
    private List<UserFavoriteGenre> userFavoriteGenres;

    @OneToMany(mappedBy = "user")
    private List<RoomFilm> roomFilms;

    public User(){

    }

    public User(Long user_id, String user_name, String user_email,
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
    }

    public User(String user_name, String user_email, String password,
                String gender, LocalDate birthday, LocalDate registration_date,
                boolean is_admin) {
        this.user_name = user_name;
        this.user_email = user_email;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
        this.registration_date = registration_date;
        this.is_admin = is_admin;
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

    public List<UserRoom> getUserRooms() {
        return userRooms;
    }

    public void setUserRooms(List<UserRoom> userRooms) {
        this.userRooms = userRooms;
    }

    public List<UserFilm> getUserFilms() {
        return userFilms;
    }

    public void setUserFilms(List<UserFilm> userFilms) {
        this.userFilms = userFilms;
    }

    public List<UserUploading> getUserUploadings() {
        return userUploadings;
    }

    public void setUserUploadings(List<UserUploading> userUploadings) {
        this.userUploadings = userUploadings;
    }

    public List<UserFavoriteGenre> getUserFavoriteGenres() {
        return userFavoriteGenres;
    }

    public void setUserFavoriteGenres(List<UserFavoriteGenre> userFavoriteGenres) {
        this.userFavoriteGenres = userFavoriteGenres;
    }

    public List<RoomFilm> getRoomFilms() {
        return roomFilms;
    }

    public void setRoomFilms(List<RoomFilm> roomFilms) {
        this.roomFilms = roomFilms;
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
}
