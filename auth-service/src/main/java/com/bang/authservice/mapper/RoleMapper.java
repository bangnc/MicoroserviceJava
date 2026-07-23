package com.bang.authservice.mapper;

import com.bang.authservice.dto.request.PermissionRequest;
import com.bang.authservice.dto.request.RoleRequest;
import com.bang.authservice.dto.response.PermissionResponse;
import com.bang.authservice.dto.response.RoleResponse;
import com.bang.authservice.entity.Permission;
import com.bang.authservice.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    Role toRole (RoleRequest request);

    RoleResponse toRoleResponse(Role role);

}
