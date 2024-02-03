package com.alexpages.firstapp.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class NoteExceptionHandler 
extends ResponseEntityExceptionHandler
{

    @ExceptionHandler(NoteManagerException500.class)
    public ResponseEntity<Object> handleExceptions(NoteManagerException500 e, WebRequest webRequest) {
        NoteManagerException exception = new NoteManagerException(e.getMessage());
        
        exception.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        exception.setTimestamp(LocalDateTime.now());
        exception.setThrowable(e.getCause());
        return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(NoteManagerException404.class)
    public ResponseEntity<Object> handleExceptions(NoteManagerException404 e, WebRequest webRequest) {
        NoteManagerException exception = new NoteManagerException(e.getMessage());
        exception.setStatus(HttpStatus.NOT_FOUND);
        exception.setTimestamp(LocalDateTime.now());
        exception.setThrowable(e.getCause());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }
}

