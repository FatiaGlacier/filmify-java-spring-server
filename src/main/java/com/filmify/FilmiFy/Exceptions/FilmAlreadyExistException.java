package com.filmify.FilmiFy.Exceptions;

public class FilmAlreadyExistException extends  RuntimeException{
    public FilmAlreadyExistException(String message) {
        super(message);
    }

    public FilmAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
