package com.sreeniii.stlayyappasannidhanamapi.model;

import com.sreeniii.stlayyappasannidhanamapi.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private Boolean isAdmin;

    private static String ADMIN_ROLE = "ADMIN_USER";

    public UserDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.isAdmin = user.getRoles().stream().filter(role -> role.getRoleName().equals(ADMIN_ROLE)).findFirst().isPresent();
    }

    public static List<UserDTO> convertTOList(List<User> users) {
        List<UserDTO> userDTOS = new ArrayList<>();
        users.forEach(user -> userDTOS.add(new UserDTO(user)));
        return userDTOS;
    }
}
