package com.bang.authservice.mapper;

import com.bang.authservice.dto.response.RoleResponse;
import com.bang.authservice.dto.response.UserResponse;
import com.bang.authservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);

    default RoleResponse map(String role) {
        RoleResponse response = new RoleResponse();
        response.setName(role);
        return response;
    }
}