package com.filmify.FilmiFy.Entities.UserRoom;

import com.filmify.FilmiFy.Entities.User.User;
import com.filmify.FilmiFy.Entities.Room.Room;
import jakarta.persistence.*;

@Entity
@Table(name = "user_room")
public class UserRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_room_id")
    private Long user_room_id;

    @ManyToOne
    @JoinColumn(name = "ur_user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ur_room_id")
    private Room room;

    public UserRoom(){

    }

    public UserRoom(Long user_room_id, User user, Room room) {
        this.user_room_id = user_room_id;
        this.user = user;
        this.room = room;
    }

    public UserRoom(User user, Room room) {
        this.user = user;
        this.room = room;
    }

    public Long getUser_room_id() {
        return user_room_id;
    }

    public void setUser_room_id(Long user_room_id) {
        this.user_room_id = user_room_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "UserRoom{" +
                "user_room_id=" + user_room_id +
                ", user_id=" + user.getUser_id() +
                ", room_id=" + room.getRoom_id() +
                '}';
    }
}
