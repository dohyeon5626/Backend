package com.security.token.service;

import com.security.token.dto.request.TokenRequest;
import com.security.token.dto.response.TokenResponse;

public interface AuthService {
    TokenResponse signIn(TokenRequest tokenRequest);
}
