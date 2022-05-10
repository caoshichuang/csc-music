package com.example.cscmusic.repository;

import com.example.cscmusic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
    User getByUsername(String username);
}