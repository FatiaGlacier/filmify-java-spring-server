package com.filmify.FilmiFy.Entities.Room;

import com.filmify.FilmiFy.Exceptions.RoomAlreadyExists;
import com.filmify.FilmiFy.Exceptions.UserNotFoundException;
import com.filmify.FilmiFy.Models.RoomModel;
import com.filmify.FilmiFy.Models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/room")
@CrossOrigin
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    @GetMapping("/get-all")
    public List<RoomModel> getAll(){
        return roomService.getAll();
    }

    @PostMapping("/add-room")
    public ResponseEntity<RoomModel> addRoom(@RequestParam(name = "user_id") Long user_id,
                                     @RequestBody Room room){
        try{
            RoomModel roomModel = roomService.addRoom(user_id, room);
            return  ResponseEntity.ok(roomModel);
        }catch (RoomAlreadyExists e){
            throw e;
        }
    }

    @GetMapping("/get-user-rooms")
    public ResponseEntity<List<RoomModel>> getUserRooms(@RequestParam(name = "user_id") Long user_id){
        try{
            UserModel userModel = roomService.getUserRooms(user_id);
            return  ResponseEntity.ok(userModel.getRooms());
        }catch (RoomAlreadyExists e){
            throw e;
        }

    }

    @PostMapping("/connect-to-room")
    public ResponseEntity<List<RoomModel>> addRoom(@RequestParam(name = "user_id") Long user_id,
                                             @RequestParam(name = "room_code") String code){
        try{
            UserModel userModel = roomService.connectToRoom(user_id, code);
            return  ResponseEntity.ok(userModel.getRooms());
        }catch (RoomAlreadyExists | UserNotFoundException e){
            throw e;
        }
    }
}
