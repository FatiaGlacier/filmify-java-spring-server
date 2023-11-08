package com.filmify.FilmiFy.Entities.RoomFilm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomFilmService {

    private final RoomFilmRepository roomFilmRepository;

    @Autowired
    public RoomFilmService(RoomFilmRepository roomFilmRepository){
        this.roomFilmRepository = roomFilmRepository;
    }
}
