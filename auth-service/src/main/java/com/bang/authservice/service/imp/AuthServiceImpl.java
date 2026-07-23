package com.bang.authservice.service.imp;

import com.bang.authservice.dto.request.LoginRequest;
import com.bang.authservice.dto.request.RegisterRequest;
import com.bang.authservice.dto.response.LoginResponse;
import com.bang.authservice.dto.response.UserResponse;
import com.bang.authservice.entity.User;
import com.bang.authservice.enums.Role;
import com.bang.authservice.mapper.UserMapper;
import com.bang.authservice.repository.UserRepository;
import com.bang.authservice.security.JwtService;
import com.bang.authservice.service.AuthService;
import jakarta.persistence.Table;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class AuthServiceImpl implements AuthService {

    private  final UserRepository userRepository;
    private  final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private  final UserMapper userMapper;
    public AuthServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder, JwtService jwtService, UserMapper userMapper){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse register(RegisterRequest request) {
        System.out.println(User.class.getAnnotation(Table.class).name());
        if(userRepository.existsByUserName(request.getUserName())){
            throw  new RuntimeException("Username already exists");
        }
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setUserName(request.getUserName());

        user.setNormalizedUserName(request.getUserName().toUpperCase());

        user.setEmail(request.getEmail());

        user.setNormalizedEmail(request.getEmail().toUpperCase());

        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        user.setEmailConfirmed(false);

        user.setPhoneNumberConfirmed(false);

        user.setTwoFactorEnabled(false);

        user.setLockoutEnabled(true);

        user.setAccessFailedCount(0);

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        //user.setRoles(roles);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository
                .findByUserName(request.getUserName())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));


        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPasswordHash())) {

            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(user);


        return new LoginResponse(
                token,
                user.getUserName()
        );
    }
}
