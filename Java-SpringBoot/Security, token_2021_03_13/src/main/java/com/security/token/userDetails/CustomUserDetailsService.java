package com.security.token.userDetails;

import com.security.token.dto.entity.User;
import com.security.token.dto.repository.UserRepository;
import com.security.token.exception.InvalidTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("UserDetails 1");
        User user = userRepository.findById(username)
                .orElseThrow(InvalidTokenException::new);
        System.out.println("DB success");
        CustomUserDetails customUserDetails = CustomUserDetails.builder()
                .ID(user.getID())
                .NAME(user.getNAME())
                .PASSWORD(user.getPASSWORD())
                .AUTHORITY(user.getAUTHORITY())
                .ENABLED(user.isENABLED())
                .build();
        return customUserDetails;
    }
}

