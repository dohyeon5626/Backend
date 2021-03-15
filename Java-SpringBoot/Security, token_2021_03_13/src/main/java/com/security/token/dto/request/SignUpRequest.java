package com.security.token.dto.request;

import lombok.Getter;

@Getter
public class SignUpRequest {
    private String id; // 아이디
    private String password; // 비밀번호
    private String name; // 이름
}
