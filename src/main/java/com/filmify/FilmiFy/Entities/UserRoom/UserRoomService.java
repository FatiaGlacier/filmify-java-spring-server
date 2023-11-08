package com.filmify.FilmiFy.Entities.UserRoom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoomService {

    private final UserRoomRepository userRoomRepository;

    @Autowired
    public UserRoomService(UserRoomRepository userRoomRepository){
        this.userRoomRepository = userRoomRepository;
    }
}
