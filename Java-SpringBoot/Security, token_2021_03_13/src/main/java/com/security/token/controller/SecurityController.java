package com.security.token.controller;

import com.security.token.dto.request.TokenRequest;
import com.security.token.dto.response.TokenResponse;
import com.security.token.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SecurityController {
    private final AuthService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse signIn(@RequestBody TokenRequest tokenRequest){
        System.out.println("controller");
        return authService.signIn(tokenRequest); // 토큰을 받아오고 반환함
    }

    @GetMapping("hello")
    public String test(){
        return "hello";
    }
}
