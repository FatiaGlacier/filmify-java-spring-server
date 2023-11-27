package com.filmify.FilmiFy.Entities.User;

import com.filmify.FilmiFy.Entities.Film.Film;
import com.filmify.FilmiFy.Entities.Genre.Genre;
import com.filmify.FilmiFy.Entities.Room.Room;
import com.filmify.FilmiFy.Entities.RoomFilm.RoomFilm;
import com.filmify.FilmiFy.Entities.UserFavoriteGenre.UserFavoriteGenre;
import com.filmify.FilmiFy.Entities.UserFilm.UserFilm;
import com.filmify.FilmiFy.Entities.UserRoom.UserRoom;
import com.filmify.FilmiFy.Entities.UserUploading.UserUploading;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Cacheable(false)
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_favorite_genre",
            joinColumns = @JoinColumn(name = "ufg_user_id"),
            inverseJoinColumns = @JoinColumn(name = "ufg_genre_id")
    )
    private List<Genre> genres;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_film",
            joinColumns = @JoinColumn(name = "uf_user_id"),
            inverseJoinColumns = @JoinColumn(name = "uf_film_id")
    )
    private List<Film> films;

//    public void addFilm(Film film) {
//        if (films == null) {
//            films = new ArrayList<>();
//        }
//        films.add(film);
//        film.getUsers().add(this);
//    }
//
//    public void removeFilm(Film film) {
//        if (films != null) {
//            films.remove(film);
//            film.getUsers().remove(this);
//        }
//    }

    @ManyToMany
    @JoinTable(
            name = "user_room",
            joinColumns = @JoinColumn(name = "ur_user_id"),
            inverseJoinColumns = @JoinColumn(name = "ur_room_id")
    )
    private List<Room> rooms;
    public User(){

    }

    //For login
    public User(String user_name, String password) {
        this.user_name = user_name;
        this.password = password;
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

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
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
