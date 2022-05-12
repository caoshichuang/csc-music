package com.example.cscmusic.service;

import com.example.cscmusic.dto.UserCreateDto;
import com.example.cscmusic.dto.UserDto;
import com.example.cscmusic.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserDto> list();

    UserDto create(UserCreateDto userCreateDto);

    @Override
    User loadUserByUsername(String username);
}
