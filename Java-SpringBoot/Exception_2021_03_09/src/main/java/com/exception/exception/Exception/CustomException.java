package com.exception.exception.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Custom Exception")
public class CustomException extends RuntimeException {
    public CustomException(String exception) {
        super("Custom Exception : "+ exception);
    }
}
