package com.filmify.FilmiFy.RoomFilm;

import com.filmify.FilmiFy.Film.Film;
import com.filmify.FilmiFy.Room.Room;
import com.filmify.FilmiFy.User.User;
import jakarta.persistence.*;

@Entity
@Table(name = "roomfilm")
public class RoomFilm {

    @Id
    @SequenceGenerator(
            name = "roomfilm_sequence",
            sequenceName = "roomfilm_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "roomfilm_sequence"
    )
    @Column(name = "room_film_id")
    private Long room_film_id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public RoomFilm(){

    }

    public RoomFilm(Long room_film_id, Room room, Film film, User user) {
        this.room_film_id = room_film_id;
        this.room = room;
        this.film = film;
        this.user = user;
    }

    public RoomFilm(Room room, Film film, User user) {
        this.room = room;
        this.film = film;
        this.user = user;
    }

    public Long getRoom_film_id() {
        return room_film_id;
    }

    public void setRoom_film_id(Long room_film_id) {
        this.room_film_id = room_film_id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "RoomFilm{" +
                "room_film_id=" + room_film_id +
                ", room_id=" + room.getRoom_id() +
                ", film_id=" + film.getFilm_id() +
                ", user_id=" + user.getUser_id() +
                '}';
    }
}
