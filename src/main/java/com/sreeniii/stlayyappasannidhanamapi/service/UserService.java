package com.sreeniii.stlayyappasannidhanamapi.service;


import com.sreeniii.stlayyappasannidhanamapi.entity.Role;
import com.sreeniii.stlayyappasannidhanamapi.entity.User;
import com.sreeniii.stlayyappasannidhanamapi.model.RegisterUserDTO;
import com.sreeniii.stlayyappasannidhanamapi.model.UpdateProfileDTO;
import com.sreeniii.stlayyappasannidhanamapi.model.UserDTO;
import com.sreeniii.stlayyappasannidhanamapi.repository.RoleRepository;
import com.sreeniii.stlayyappasannidhanamapi.repository.UserRepository;
import com.sreeniii.stlayyappasannidhanamapi.util.Constants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<UserDTO> findAllUsers() {
        List<User> users = (List<User>) userRepository.findAll();
        return UserDTO.convertTOList(users);
    }

    public User createUser(RegisterUserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        Role role = roleRepository.findByRoleName(Constants.STANDARD_USER_ROLE);
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    public UserDTO updateProfile(UUID userId, UpdateProfileDTO updateProfileDTO) {
        User user = userRepository.findById(userId).get();
        user.setFirstName(updateProfileDTO.getFirstName());
        user.setLastName(updateProfileDTO.getLastName());

        user = userRepository.save(user);
        return new UserDTO(user);
    }

    public void toggleAdminRights(UUID userId, Boolean status) {
        User user = userRepository.findById(userId).get();

        Role adminRole = roleRepository.findByRoleName(Constants.ADMIN_ROLE);
        if(Boolean.TRUE.equals(status)) {
            if(!user.getRoles().contains(adminRole)) {
                user.getRoles().add(adminRole);
            }
        } else {
            user.getRoles().remove(adminRole);
        }

        userRepository.save(user);
    }

    public UserDTO getUserInfoByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return new UserDTO(user);
    }

    public UserDTO getUserById(UUID userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            return new UserDTO(optionalUser.get());
        }
        return null;
    }
}
