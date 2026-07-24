package com.bang.authservice.service.imp;

import com.bang.authservice.dto.request.UserUpdateRequest;
import com.bang.authservice.dto.response.UserResponse;
import com.bang.authservice.entity.User;
import com.bang.authservice.exception.AppException;
import com.bang.authservice.exception.ErrorCode;
import com.bang.authservice.mapper.UserMapper;
import com.bang.authservice.repository.RoleRepository;
import com.bang.authservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    BCryptPasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

//    @PreAuthorize("hasRole('ROLE')")
    @PreAuthorize("hasAuthority('APPROVE_POST')")
    public List<UserResponse> getUsers() {
        log.info("In method get Users");
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext().getAuthentication();
        String name = context.getName();
        User user =  userRepository.findByUserName(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, request);
        //user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        User saved = userRepository.findById(user.getId())
                .orElseThrow();

        return userMapper.toUserResponse(saved);
    }
}
