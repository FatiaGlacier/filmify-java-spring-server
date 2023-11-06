package com.filmify.FilmiFy.UserRoom;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/user-room")
public class UserRoomController {

    private final UserRoomService userRoomService;

    public UserRoomController(UserRoomService userRoomService){
        this.userRoomService = userRoomService;
    }
}
