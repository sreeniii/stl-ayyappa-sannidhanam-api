package com.sreeniii.stlayyappasannidhanamapi.repository;

import com.sreeniii.stlayyappasannidhanamapi.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RoleRepository extends CrudRepository<Role, UUID> {
    Role findByRoleName(String roleName);
}
