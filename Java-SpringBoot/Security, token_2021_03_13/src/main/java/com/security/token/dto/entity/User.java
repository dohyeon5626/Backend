package com.security.token.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String ID; // 아이디
    private String PASSWORD; // 비밀번호
    private String NAME; // 이름
    private String AUTHORITY; // 권한
    private boolean ENABLED; // 활성화
}
