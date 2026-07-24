package com.bang.authservice.mapper;

import com.bang.authservice.dto.request.UserUpdateRequest;
import com.bang.authservice.dto.response.RoleResponse;
import com.bang.authservice.dto.response.UserResponse;
import com.bang.authservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}