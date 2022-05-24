package com.example.cscmusic.service;

import com.example.cscmusic.dto.TokenCreateRequest;
import com.example.cscmusic.dto.UserCreateRequest;
import com.example.cscmusic.dto.UserDto;
import com.example.cscmusic.dto.UserUpdateRequest;
import com.example.cscmusic.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author caoshichuang
 */
public interface UserService extends UserDetailsService {

  UserDto create(UserCreateRequest userCreateRequest);

  @Override
  User loadUserByUsername(String username);

  UserDto get(String id);

  UserDto update(String id, UserUpdateRequest userUpdateRequest);

  void delete(String id);

  Page<UserDto> search(Pageable pageable);

  String createToken(TokenCreateRequest tokenCreateRequest);

  UserDto getCurrentUser();
}
