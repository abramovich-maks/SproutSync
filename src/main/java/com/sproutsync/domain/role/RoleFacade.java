package com.sproutsync.domain.role;

import com.sproutsync.domain.role.dto.RoleResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
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
