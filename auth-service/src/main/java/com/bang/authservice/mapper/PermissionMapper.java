package com.bang.authservice.mapper;

import com.bang.authservice.dto.request.PermissionRequest;
import com.bang.authservice.dto.response.PermissionResponse;
import com.bang.authservice.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission (PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);

}
