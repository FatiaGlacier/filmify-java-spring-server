package com.filmify.FilmiFy.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
       ApiException apiException = new ApiException(
               e.getMessage(),
               badRequest,
               ZonedDateTime.now(ZoneId.of("Europe/Kiev"))
       );

       return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(GenreNotFoundException.class)
    public ResponseEntity<Object> handleGenreNotFoundException(GenreNotFoundException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Europe/Kiev"))
        );

        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<Object> handleWrongPasswordException(WrongPasswordException e){
        HttpStatus unauthorized = HttpStatus.UNAUTHORIZED;
        ApiException apiException = new ApiException(
                e.getMessage(),
                unauthorized,
                ZonedDateTime.now(ZoneId.of("Europe/Kiev"))
        );

        return new ResponseEntity<>(apiException, unauthorized);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleWrongPasswordException(UserAlreadyExistsException e){
        HttpStatus unauthorized = HttpStatus.UNAUTHORIZED;
        ApiException apiException = new ApiException(
                e.getMessage(),
                unauthorized,
                ZonedDateTime.now(ZoneId.of("Europe/Kiev"))
        );

        return new ResponseEntity<>(apiException, unauthorized);
    }

    @ExceptionHandler(RoomAlreadyExists.class)
    public ResponseEntity<Object> handleWrongPasswordException(RoomAlreadyExists e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Europe/Kiev"))
        );

        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(FilmAlreadyExistException.class)
    public ResponseEntity<Object> handleWrongPasswordException(FilmAlreadyExistException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Europe/Kiev"))
        );

        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(FilmNotFoundException.class)
    public ResponseEntity<Object> handleWrongPasswordException(FilmNotFoundException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Europe/Kiev"))
        );

        return new ResponseEntity<>(apiException, badRequest);
    }

}
