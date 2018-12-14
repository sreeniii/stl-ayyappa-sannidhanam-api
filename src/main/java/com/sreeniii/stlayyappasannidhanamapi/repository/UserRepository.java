package com.sreeniii.stlayyappasannidhanamapi.repository;


import com.sreeniii.stlayyappasannidhanamapi.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
