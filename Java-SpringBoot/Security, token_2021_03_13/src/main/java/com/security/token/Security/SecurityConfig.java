package com.security.token.Security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll() // "/" 링크는 모든 사용자에게 허락함
                .antMatchers(HttpMethod.POST, "/auth", "/auth/userNew").permitAll() // post로 요청이 주어지는 "/auth"링크는 모든 사용자에게 허락함
                .antMatchers( "/auth/user").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/auth/admin").hasAnyAuthority("ADMIN")
        .and().authorizeRequests()
                .anyRequest().authenticated() // 모든 요청은 인증이 있어야 함
        .and().csrf().disable() //.csrf()설정은 CSRF 공격을 막기위해 세팅되어있는데 .disable()을 세팅해 놓지 않으면 해당 작업을 수행하기 위한 파라미터가 없다고 에러가 발생하기 때문에 .disable()을 해주도록 한다.
                .sessionManagement().disable() // 최대 접속 수 비활성화
                .cors().disable() // cors 비활성화
                .formLogin()//.disable() // 폼 로그인 이용 여부
                .defaultSuccessUrl("/auth/user") //  로그인 성공시 url
        .and().logout().logoutUrl("/auth/logout") // 로그아웃 url // 이 url로 post 요청을 보내면 로그아웃 됨 (빈 json "{}" 을 보내면 됨)
                .logoutSuccessUrl("/auth/login") // 로그아웃 성공시 url
                .and()
                .apply(new JwtConfigure(jwtTokenProvider));
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

