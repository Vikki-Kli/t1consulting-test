package ru.t1consulting.symbolsoccurrence.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<String> handleEmptyTextException() {
        String bodyOfResponse = "Oops, you forgot to add text to the request body";
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.BAD_REQUEST);
    }
}
