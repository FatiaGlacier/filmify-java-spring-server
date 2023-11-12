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
import java.util.Random;

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
    }


}
