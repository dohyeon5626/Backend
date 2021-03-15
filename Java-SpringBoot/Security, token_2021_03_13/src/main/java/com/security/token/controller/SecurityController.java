package com.security.token.controller;

import com.security.token.dto.entity.User;
import com.security.token.dto.repository.UserRepository;
import com.security.token.dto.request.SignUpRequest;
import com.security.token.dto.request.TokenRequest;
import com.security.token.dto.response.TokenResponse;
import com.security.token.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SecurityController {

    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse signIn(@RequestBody TokenRequest tokenRequest){ // 로그인
        System.out.println("controller");
        return authService.signIn(tokenRequest); // 토큰을 받아오고 반환함
    }

    @GetMapping("hello") // 테스트용 인증이 필요한 페이지
    public String testHello(){
        return "hello";
    }

    @GetMapping("/admin") // 테스트용 인증이 필요한 페이지
    public String testAdmin(){
        return "admin";
    }

    @PostMapping("new")
    public void signUp(@RequestBody SignUpRequest signUpRequest){ // 유저 회원가입
        authService.signUp(signUpRequest);
    }
}
