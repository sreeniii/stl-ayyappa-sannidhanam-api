package com.sreeniii.stlayyappasannidhanamapi.model;

import lombok.Data;

@Data
public class RegisterUserDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
