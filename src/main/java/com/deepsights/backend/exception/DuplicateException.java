package com.deepsights.backend.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class DuplicateException extends RuntimeException{

    private Integer status;

    public DuplicateException(String message){
        super(message);
        this.status=HttpStatus.CONFLICT.value();
    }

}
