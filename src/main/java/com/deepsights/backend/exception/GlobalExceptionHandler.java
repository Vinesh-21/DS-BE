package com.deepsights.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex){

        ErrorResponse error = new ErrorResponse(ex.getMessage(), ex.getStatus());

        return ResponseEntity.status(ex.getStatus()).body(error);
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateException(DuplicateException ex){

        ErrorResponse error = new ErrorResponse(ex.getMessage(), ex.getStatus());

        return ResponseEntity.status(ex.getStatus()).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex){

        ex.printStackTrace();

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                500
        );

        return ResponseEntity.status(500).body(error);
    }
}
