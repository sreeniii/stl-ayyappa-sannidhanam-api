package com.sreeniii.stlayyappasannidhanamapi.controller;

import com.sreeniii.stlayyappasannidhanamapi.model.RegisterUserDTO;
import com.sreeniii.stlayyappasannidhanamapi.model.UpdateProfileDTO;
import com.sreeniii.stlayyappasannidhanamapi.model.UserDTO;
import com.sreeniii.stlayyappasannidhanamapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID userId) {
        UserDTO userDTO = userService.getUserById(userId);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateProfile(@PathVariable UUID userId, @RequestBody UpdateProfileDTO updateProfileDTO) {
        UserDTO userDTO = userService.updateProfile(userId, updateProfileDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PatchMapping("/{userId}/admin/{status}")
    public ResponseEntity toggleAdminRights(@PathVariable UUID userId, @PathVariable Boolean status) {
        userService.toggleAdminRights(userId, status);
        return new ResponseEntity(HttpStatus.OK);
    }
}
