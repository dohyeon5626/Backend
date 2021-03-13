package com.security.token.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request); // 토큰을 가져옴
        if(token != null && jwtTokenProvider.validate(token)) { // 토큰의 유효성 검증
            Authentication authentication = jwtTokenProvider.getAuthentication(token); // 토큰에 따른 시큐리티 전용 객체를 받아옴 (로그인 인증)
            SecurityContextHolder.getContext().setAuthentication(authentication); // SecurityContextHolder 객체에 접근 주체를 담음
        }
        chain.doFilter(request,response); // 다음필터로 이동 or 없으면, 서블릿의 service 호출함
    }
}
