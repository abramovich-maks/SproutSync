package com.sproutsync.domain.role;

import com.sproutsync.domain.role.dto.RoleResponseDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class RoleRetriever {

    private final RoleRepository roleRepository;

    public RoleResponseDto findRoleById(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Role with id [" + roleId + "] not found"));
        return RoleResponseDto.builder()
                .roleId(role.getId())
                .role(role.getName())
                .build();
    }

    RoleResponseDto findRoleByName(final String roleName) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalArgumentException("Role [" + roleName + "] not found"));
        return RoleResponseDto.builder()
                .roleId(role.getId())
                .role(role.getName())
                .build();
    }
}
