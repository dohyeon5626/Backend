package com.security.token.controller;

import com.security.token.dto.request.SignUpRequest;
import com.security.token.dto.request.TokenRequest;
import com.security.token.dto.response.TokenResponse;
import com.security.token.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SecurityController {

    private final AuthService authService;

    @GetMapping("/user") // 유저 인증이 필요한 페이지
    public String testHello(){
        return "user";
    }

    @GetMapping("/admin") // 어드민 인증이 필요한 페이지
    public String testAdmin(){
        return "admin";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse signIn(@RequestBody TokenRequest tokenRequest){ // 로그인
        System.out.println("controller");
        return authService.signIn(tokenRequest); // 토큰을 받아오고 반환함
    }

    @PostMapping("/userNew") // 유저 회원가입
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody SignUpRequest signUpRequest){ // 유저 회원가입
        authService.signUp(signUpRequest);
    }
}
