package com.filmify.FilmiFy.RoomFilm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/room-film")
public class RoomFilmController {

    private final RoomFilmService roomFilmService;

    @Autowired
    public RoomFilmController(RoomFilmService roomFilmService){
        this.roomFilmService = roomFilmService;
    }
}
