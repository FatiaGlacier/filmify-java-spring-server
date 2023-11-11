package com.filmify.FilmiFy.Entities.Room;

import com.filmify.FilmiFy.Entities.User.User;
import com.filmify.FilmiFy.Entities.User.UserRepository;
import com.filmify.FilmiFy.Exceptions.RoomAlreadyExists;
import com.filmify.FilmiFy.Exceptions.UserNotFoundException;
import com.filmify.FilmiFy.Models.RoomModel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    private final UserRepository userRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, UserRepository userRepository){
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    public List<RoomModel> getAll() {
        List<Room> rooms = roomRepository.findAll();
        List<RoomModel> roomModels = new ArrayList<>();
        for(Room room: rooms){
            roomModels.add(RoomModel.toModel(room));
        }

        return roomModels;
    }

    public void addRoom(Long user_id, Room room) {
        Optional<User> foundedUser = userRepository.findById(user_id);
        if(foundedUser.isEmpty()){
            throw new UserNotFoundException("User not found, wrong ID: " + user_id);
        }

        Optional<Room> foundedRoom = roomRepository.findRoomByCode(room.getRoom_code());
        if(foundedRoom.isPresent()){
            throw new RoomAlreadyExists("Code already exists: " + room.getRoom_code());
        }
        User user = foundedUser.get();
        user.getRooms().add(room);

        roomRepository.save(room);
    }


}
