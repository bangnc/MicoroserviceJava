package com.bang.authservice.controller;

import com.bang.authservice.dto.request.LoginRequest;
import com.bang.authservice.dto.request.RegisterRequest;
import com.bang.authservice.dto.response.LoginResponse;
import com.bang.authservice.dto.response.UserResponse;
import com.bang.authservice.service.AuthService;
import com.bang.common.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ApiResponse<UserResponse> register(@RequestBody RegisterRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(authService.register(request))
                .build();

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                authService.login(request)
        );
    }
}
