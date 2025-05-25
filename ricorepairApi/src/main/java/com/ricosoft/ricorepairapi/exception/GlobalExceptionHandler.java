package com.ricosoft.ricorepairapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDetails> handleBadCredentialsException(BadCredentialsException ex,
                                                                      WebRequest webRequest) {
        ErrorDetails error = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(), // El mensaje de error de BadCredentialsException
                webRequest.getDescription(false),
                "BAD CREDENTIALS"
        );

        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED); // 401 Unauthorized
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex,
                                                                        WebRequest webRequest){
        ErrorDetails error = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                webRequest.getDescription(false),
                "NOT FOUND"
                );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }
}
