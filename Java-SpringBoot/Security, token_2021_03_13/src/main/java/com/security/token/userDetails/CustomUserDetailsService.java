package com.security.token.userDetails;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("UserDetails 1");
        CustomUserDetails user = new CustomUserDetails(); // 데이터 베이스 연동해야 함 (아이디에 맞는 객체를 받아와야 함)
        System.out.println("UserDetails 2");
        user.setID("hello");
        user.setNAME("hello");
        user.setPASSWORD("hello");
        user.setAUTHORITY("");
        user.setENABLED(true); //

        if(user==null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}

