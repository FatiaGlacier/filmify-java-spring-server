package com.filmify.FilmiFy.Entities.Room;

import com.filmify.FilmiFy.Exceptions.RoomAlreadyExists;
import com.filmify.FilmiFy.Models.RoomModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/room")
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
    public void addRoom(@RequestParam(name = "user_id") Long user_id,
                                     @RequestBody Room room){
        try{
            roomService.addRoom(user_id, room);
        }catch (RoomAlreadyExists e){
            throw e;
        }

    }
}
