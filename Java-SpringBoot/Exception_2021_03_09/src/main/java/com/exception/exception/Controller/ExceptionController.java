package com.exception.exception.Controller;

import com.exception.exception.Exception.CustomException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {
    @GetMapping("/{exception}")
    public void makeException(@PathVariable("exception") String exception){
        throw new CustomException(exception);
    }
}
