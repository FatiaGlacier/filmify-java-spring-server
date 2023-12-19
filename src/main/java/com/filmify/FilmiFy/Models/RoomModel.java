package com.filmify.FilmiFy.Models;

import com.filmify.FilmiFy.Entities.Room.Room;
import com.filmify.FilmiFy.Entities.RoomFilm.RoomFilm;
import com.filmify.FilmiFy.Entities.User.User;
import com.filmify.FilmiFy.Entities.UserRoom.UserRoom;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoomModel {

    private Long room_id;

    private String room_code;

    private String room_name;

    private LocalDate creation_date;

    private List<UserModel> userRooms;

    private List<RoomFilm> roomFilms;

    private List<String> userNames;

    public RoomModel() {
        this.userNames = new ArrayList<>();
    }

    public RoomModel(Long room_id, String room_code,
                     String room_name, LocalDate creation_date) {
        this.room_id = room_id;
        this.room_code = room_code;
        this.room_name = room_name;
        this.creation_date = creation_date;
        this.userNames = new ArrayList<>();
    }

    public RoomModel(String room_code, String room_name, LocalDate creation_date) {
        this.room_code = room_code;
        this.room_name = room_name;
        this.creation_date = creation_date;
        this.userNames = new ArrayList<>();
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

    public List<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
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

    public static RoomModel toModel(Room room){
        RoomModel model = new RoomModel();
        model.setRoom_id(room.getRoom_id());
        model.setRoom_code(room.getRoom_code());
        model.setRoom_name(room.getRoom_name());
        model.setRoom_id(room.getRoom_id());
        model.setCreation_date(room.getCreation_date());
        if(room.getUserRooms() != null){
            for(UserRoom userRoom: room.getUserRooms()){
                model.getUserNames().add(userRoom.getUser().getUser_name());
            }
        }else{
            model.setUserNames(new ArrayList<>());
        }
        return model;
    }

}
