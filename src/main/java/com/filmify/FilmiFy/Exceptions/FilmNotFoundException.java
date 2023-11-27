package com.filmify.FilmiFy.Exceptions;

public class FilmNotFoundException extends  RuntimeException{
    public FilmNotFoundException(String message) {
        super(message);
    }

    public FilmNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
