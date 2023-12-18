package com.filmify.FilmiFy.Entities.Room;

import com.filmify.FilmiFy.Entities.User.User;
import com.filmify.FilmiFy.Entities.User.UserRepository;
import com.filmify.FilmiFy.Entities.UserRoom.UserRoom;
import com.filmify.FilmiFy.Entities.UserRoom.UserRoomRepository;
import com.filmify.FilmiFy.Exceptions.RoomAlreadyExists;
import com.filmify.FilmiFy.Exceptions.RoomNotFoundException;
import com.filmify.FilmiFy.Exceptions.UserNotFoundException;
import com.filmify.FilmiFy.Models.RoomModel;
import com.filmify.FilmiFy.Models.UserModel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    private final UserRepository userRepository;

    private final UserRoomRepository userRoomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, UserRepository userRepository, UserRoomRepository userRoomRepository){
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.userRoomRepository = userRoomRepository;
    }

    public List<RoomModel> getAll() {
        List<Room> rooms = roomRepository.findAll();
        List<RoomModel> roomModels = new ArrayList<>();
        for(Room room: rooms){
            roomModels.add(RoomModel.toModel(room));
        }

        return roomModels;
    }

    public RoomModel addRoom(Long user_id, Room room) {
        Optional<User> foundedUser = userRepository.findById(user_id);
        if(foundedUser.isEmpty()){
            throw new UserNotFoundException("User not found, wrong ID: " + user_id);
        }
        Random  random = new Random();
        Optional<Room> foundedRoom;
        String code;
        while(true){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 6; i++){
                sb.append(random.nextInt(10));
            }
            code = sb.toString();
            foundedRoom = roomRepository.findRoomByCode(code);
            if(foundedRoom.isEmpty()){
                break;
            }
        }

        User user = foundedUser.get();
        room.setRoom_code(code);
        user.getRooms().add(room);
        roomRepository.save(room);

        return RoomModel.toModel(room);
    }


    public UserModel getUserRooms(Long user_id) {
        Optional<User> foundedUser = userRepository.findById(user_id);
        if(foundedUser.isEmpty()){
            throw new UserNotFoundException("User not found, wrong ID: " + user_id);
        }
        User user = foundedUser.get();
        UserModel userModel = UserModel.toModel(user);
        return userModel;
    }

    public UserModel connectToRoom(Long user_id, String code) {
        Optional<User> foundUser = userRepository.findById(user_id);
        if(foundUser.isEmpty()){
            throw new UserNotFoundException("User not found, wrong ID: " + user_id);
        }

        Optional<Room> foundRoom = roomRepository.findRoomByCode(code);
        if(foundRoom.isEmpty()){
            throw new RoomNotFoundException("Room not found, wrong code: " + code);
        }

        User user = foundUser.get();
        Room room = foundRoom.get();

        for(UserRoom userRoom: room.getUserRooms()){
            if(userRoom.getUser().getUser_id().equals(user_id)){
                throw new RoomAlreadyExists("User already in room, ID: " + user_id + ", room: " + code);
            }
        }

        UserRoom userRoom = new UserRoom();
        userRoom.setUser(user);
        userRoom.setRoom(room);

        user.getUserRooms().add(userRoom);
        room.getUserRooms().add(userRoom);

        userRoomRepository.saveAndFlush(userRoom);

        UserModel userModel = UserModel.toModel(user);
        return userModel;
    }
}
