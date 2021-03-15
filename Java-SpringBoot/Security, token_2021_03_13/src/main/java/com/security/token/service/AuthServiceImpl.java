package com.security.token.service;

import com.security.token.Security.JwtTokenProvider;
import com.security.token.dto.entity.User;
import com.security.token.dto.repository.UserRepository;
import com.security.token.dto.request.SignUpRequest;
import com.security.token.dto.request.TokenRequest;
import com.security.token.dto.response.TokenResponse;
import com.security.token.exception.InvalidTokenException;
import com.security.token.userDetails.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenResponse signIn(TokenRequest tokenRequest){ // 로그인
        System.out.println("service");
        UserDetails userDetails = userDetailsService.loadUserByUsername(tokenRequest.getId()); // 아이디에 알맞는 객체를 불러옴
        System.out.println("userDetails End");

        if(!passwordEncoder.matches(tokenRequest.getPassword(),userDetails.getPassword())) { // 불러온 객체와 비밀번호가 맞는지 확인 (아직 비밀번호 암호화 안함)
            throw new InvalidTokenException();
        }
        System.out.println("passwordPass");

        String refreshToken = jwtTokenProvider.generateRefreshToken(tokenRequest.getId()); // refresh 토큰 받아오기
        System.out.println("refreshPass");
        String accessToken = jwtTokenProvider.generateAccessToken(tokenRequest.getId()); // access 토큰 받아오기
        System.out.println("accessPass");
        return new TokenResponse(accessToken, refreshToken); // 각 토큰을 반환함
    }

    @Override
    public void signUp(SignUpRequest signUpRequest){ // 유저 회원가입
        User user = User.builder()
                .ID(signUpRequest.getId())
                .NAME(signUpRequest.getName())
                .PASSWORD(passwordEncoder.encode(signUpRequest.getPassword()))
                .AUTHORITY("user")
                .ENABLED(true)
                .build();
        userRepository.save(user);
    }
}
