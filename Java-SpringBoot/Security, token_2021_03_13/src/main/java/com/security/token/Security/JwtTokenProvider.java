package com.security.token.Security;

import com.security.token.exception.InvalidTokenException;
import com.security.token.userDetails.CustomUserDetails;
import com.security.token.userDetails.CustomUserDetailsService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final CustomUserDetailsService userDetailsService;

    @Value("${auth.jwt.secret}")
    private String secretKey; // 비밀키는 들키면 안되므로 숨겨놓음, 비밀키가 너무 짧아도 안됨

    public String generateAccessToken(String id) { // 아이디에 따른 access 토큰 부여
        System.out.println("accessStart"+" "+id+" "+secretKey);
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + (7200 * 1000))) // 시간설정 (대략 2시간)
                .signWith(SignatureAlgorithm.HS512, secretKey) // 해시 알고리즘을 HS512의 방법으로, 비밀키를 secretKey로 함 (보안적인 요소에서 secretkey를 모르면 뚫기 힘드므로 매우 중요함)
                .setIssuedAt(new Date()) // 현재시간 설정
                .setSubject(id) // 숨길 아이디 (아이디를 해시알고리즘을 이용해 숨김)
                .claim("type", "access") // access 토큰으로 설정
                .compact();
    }

    public String generateRefreshToken(String id) { // 아이디에 따른 refresh 토큰 부여
        System.out.println("refreshStart"+" "+id+" "+secretKey);
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + (172800 * 1000))) // 시간설정 (대략 2주일)
                .signWith(SignatureAlgorithm.HS512,secretKey) // 해시 알고리즘을 HS512의 방법으로, 비밀키를 secretKey로 함 (보안적인 요소에서 secretkey를 모르면 뚫기 힘드므로 매우 중요함)
                .setIssuedAt(new Date()) // 현재시간 설정
                .setSubject(id) // 숨길 아이디 (아이디를 해시알고리즘을 이용해 숨김)
                .claim("type", "refresh") // refresh 토큰으로 설정
                .compact();
    }

    public boolean validate(String token) { // 토큰의 유효성 검증
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject(); // 비밀키와 토큰을에 따라 파싱을 해서, subject(id)를 가져옴
            return true; // 가져오는 과정에 문제가 안생기면 true 반환됨
        } catch (Exception e) { // 가져오는 과정에서 문제 생김
            throw new InvalidTokenException();
        }
    }

    public String getId(String token) { // 토큰에 따른 아이디 반환
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject(); // 비밀키와 토큰을에 따라 파싱을 해서, subject(id)를 가져오고 반환함
        } catch (Exception e) { // 가져오는 과정에서 문제 생김
            throw new InvalidTokenException();
        }
    }

    public boolean isRefresh(String token) { // refresh 토큰인지 검증
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("type").equals("refresh"); // 비밀키와 토큰을에 따라 파싱을 해서, refresh 토큰의 참/거짓을 반환
        } catch (Exception e) { // 가져오는 과정에서 문제 생김
            throw new InvalidTokenException();
        }
    }

    public String resolveToken(HttpServletRequest request) { // 토큰을 가져옴
        String token = request.getHeader("Authorization"); // 헤더의 Authorization에서 토큰(아직 가공안됨)을 받아옴
        if(token != null && token.startsWith("Bearer")) { // 가공이 안된 토큰이 유효한지 확인
            return token.substring(7); // 가공해서 토큰을 반환 // 기본적으로 우리가 쓰는 토큰의 앞에는 Bearer ~~~ 형식으로 되어있음
        }
        return null; // Bearer 토큰이 아님
    }

    public Authentication getAuthentication(String token) { // 시큐리티 전용 객체를 만들어줌
        CustomUserDetails authDetails = userDetailsService.loadUserByUsername(getId(token)); // 토큰에 따른 유저 정보 찾아옴
        return new UsernamePasswordAuthenticationToken(authDetails, "", authDetails.getAuthorities()); // 토큰에 따른 유저 정보와 권한을 묶어서 토큰을 만들어 반환
    }

}
