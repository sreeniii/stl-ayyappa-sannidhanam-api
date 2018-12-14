package com.sreeniii.stlayyappasannidhanamapi.service.impl;


import com.sreeniii.stlayyappasannidhanamapi.entity.User;
import com.sreeniii.stlayyappasannidhanamapi.repository.UserRepository;
import com.sreeniii.stlayyappasannidhanamapi.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenericServiceImpl implements GenericService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }
}
