package com.sreeniii.stlayyappasannidhanamapi.model;

import com.sreeniii.stlayyappasannidhanamapi.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class RoleDTO {
    private String roleName;
    private String description;

    private RoleDTO(Role role) {
        this.roleName = role.getRoleName();
        this.description = role.getDescription();
    }

    private List<RoleDTO> convertToList(List<Role> roles) {
        List<RoleDTO> roleDTOS = new ArrayList<>();
        roles.forEach(role -> roleDTOS.add(new RoleDTO(role)));
        return roleDTOS;
    }
}
