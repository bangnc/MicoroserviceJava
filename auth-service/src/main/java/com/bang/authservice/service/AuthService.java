package com.bang.authservice.service;

import com.bang.authservice.dto.request.LoginRequest;
import com.bang.authservice.dto.request.RegisterRequest;
import com.bang.authservice.dto.response.LoginResponse;

public interface AuthService {
    void register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
