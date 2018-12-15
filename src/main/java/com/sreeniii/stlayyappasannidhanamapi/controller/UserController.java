package com.sreeniii.stlayyappasannidhanamapi.controller;

import com.sreeniii.stlayyappasannidhanamapi.model.RegisterUserDTO;
import com.sreeniii.stlayyappasannidhanamapi.model.UserDTO;
import com.sreeniii.stlayyappasannidhanamapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody RegisterUserDTO userDTO) {
        userService.createUser(userDTO);
        return  new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> userDTOS = userService.findAllUsers();
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }
}
