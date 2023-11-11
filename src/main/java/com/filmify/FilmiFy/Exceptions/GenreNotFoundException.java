package com.filmify.FilmiFy.Exceptions;

public class GenreNotFoundException extends  RuntimeException{
    public GenreNotFoundException(String message) {
        super(message);
    }

    public GenreNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
