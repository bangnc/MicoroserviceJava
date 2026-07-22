package com.bang.authservice.service;

import com.bang.authservice.dto.request.LoginRequest;
import com.bang.authservice.dto.request.RegisterRequest;
import com.bang.authservice.dto.response.LoginResponse;
import com.bang.authservice.dto.response.UserResponse;

public interface AuthService {
    UserResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
