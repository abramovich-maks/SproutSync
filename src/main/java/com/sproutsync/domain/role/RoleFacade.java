package com.sproutsync.domain.role;

import com.sproutsync.domain.role.dto.RoleResponseDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoleFacade {

    private final RoleRetriever roleRetriever;

    public RoleResponseDto findRoleById(Long roleId) {
        return roleRetriever.findRoleById(roleId);
    }

    public RoleResponseDto findRoleByName(String roleName) {
        return roleRetriever.findRoleByName(roleName);
    }
}
