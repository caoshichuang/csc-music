package com.example.cscmusic.service;

import com.example.cscmusic.Mapper.UserMapper;
import com.example.cscmusic.dto.UserDto;
import com.example.cscmusic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    UserRepository repository;
    UserMapper userMapper;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> list() {
        return repository.findAll()
                .stream().map(userMapper::toDto).collect(Collectors.toList());
    }

}
