package com.sreeniii.stlayyappasannidhanamapi.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name="app_role")
public class Role {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "role_id")
    private UUID roleId;

    @Column(name="role_name")
    private String roleName;

    @Column(name="description")
    private String description;
}
