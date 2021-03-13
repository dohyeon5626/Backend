package com.security.token.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Invalid Token Exception")
public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("Invalid Token"); // 유효하지 않은 토큰
    }
}
