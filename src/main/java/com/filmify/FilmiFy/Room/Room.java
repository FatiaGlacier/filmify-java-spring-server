package com.filmify.FilmiFy.Room;

import com.filmify.FilmiFy.RoomFilm.RoomFilm;
import com.filmify.FilmiFy.UserFilm.UserFilm;
import com.filmify.FilmiFy.UserRoom.UserRoom;
import com.filmify.FilmiFy.UserUploading.UserUploading;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @SequenceGenerator(
            name = "room_sequence",
            sequenceName = "room_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "room_sequence"
    )
    @Column(name = "room_id")
    private Long room_id;

    @Column(name = "room_code")
    private String room_code;

    @Column(name = "room_name")
    private String room_name;

    @Column(name = "creation_date")
    private LocalDate creation_date;


    @OneToMany(mappedBy = "room")
    private List<UserRoom> userRooms;

    @OneToMany(mappedBy = "room")
    private List<RoomFilm> roomFilms;

    public Room() {

    }

    public Room(Long room_id, String room_code,
                String room_name, LocalDate creation_date) {
        this.room_id = room_id;
        this.room_code = room_code;
        this.room_name = room_name;
        this.creation_date = creation_date;
    }

    public Room(String room_code, String room_name, LocalDate creation_date) {
        this.room_code = room_code;
        this.room_name = room_name;
        this.creation_date = creation_date;
    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public String getRoom_code() {
        return room_code;
    }

    public void setRoom_code(String room_code) {
        this.room_code = room_code;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    @Override
    public String toString() {
        return "Room{" +
                "room_id=" + room_id +
                ", room_code='" + room_code + '\'' +
                ", room_name='" + room_name + '\'' +
                ", creation_date=" + creation_date +
                '}';
    }
}
