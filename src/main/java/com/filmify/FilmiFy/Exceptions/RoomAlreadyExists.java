package com.filmify.FilmiFy.Exceptions;

public class RoomAlreadyExists extends  RuntimeException{
    public RoomAlreadyExists(String message) {
        super(message);
    }

    public RoomAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }
}