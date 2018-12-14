package com.sreeniii.stlayyappasannidhanamapi.service;


import com.sreeniii.stlayyappasannidhanamapi.entity.User;

import java.util.List;

public interface GenericService {
    User findByUsername(String username);

    List<User> findAllUsers();
}
