package com.sproutsync.domain.loginandregister;

import com.sproutsync.domain.role.Role;
import com.sproutsync.domain.role.dto.RoleResponseDto;

class LoginAndRegisterMapper {

    public static Role mapFromRoleResponseDtoToRole(final RoleResponseDto defaultRole) {
        return new Role(defaultRole.roleId(), defaultRole.role());
    }
}
