package com.deepsights.backend.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class NotFoundException extends RuntimeException{

    private Integer status;

    public NotFoundException(String message){
        super(message);
        this.status= HttpStatus.NOT_FOUND.value();
    }
}