package com.example.cscmusic.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.cscmusic.Mapper.UserMapper;
import com.example.cscmusic.config.SecurityConfig;
import com.example.cscmusic.dto.TokenCreateRequest;
import com.example.cscmusic.dto.UserCreateRequest;
import com.example.cscmusic.dto.UserDto;
import com.example.cscmusic.dto.UserUpdateRequest;
import com.example.cscmusic.entity.User;
import com.example.cscmusic.exception.BizException;
import com.example.cscmusic.exception.ExceptionType;
import com.example.cscmusic.repository.UserRepository;
import com.example.cscmusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author caoshichuang
 */
@Service
public class UserServiceImpl implements UserService {

  UserRepository repository;
  UserMapper userMapper;
  PasswordEncoder passwordEncoder;

  @Override
  public UserDto create(UserCreateRequest userCreateRequest) {
    checkUserName(userCreateRequest.getUsername());
    User user = userMapper.createEntity(userCreateRequest);
    user.setPassword(passwordEncoder.encode(user.getPassword())); // 对传入的密码进行加密
    return userMapper.toDto(repository.save(user));
  }

  @Override
  public User loadUserByUsername(String username) {
    Optional<User> user = repository.findByUsername(username);
    if (!user.isPresent()) {
      throw new BizException(ExceptionType.USER_NOT_FOUND);
    }
    return user.get();
  }

  @Override
  public UserDto get(String id) {
    // TODO:重构代码
    Optional<User> user = repository.findById(id);
    if (!user.isPresent()) {
      throw new BizException(ExceptionType.USER_NOT_FOUND);
    }
    return userMapper.toDto(user.get());
  }

  @Override
  public UserDto update(String id, UserUpdateRequest userUpdateRequest) {
    // TODO:重构代码
    Optional<User> user = repository.findById(id);
    if (!user.isPresent()) {
      throw new BizException(ExceptionType.USER_NOT_FOUND);
    }
    return userMapper.toDto(
        repository.save(userMapper.updateEntity(user.get(), userUpdateRequest)));
  }

  @Override
  public void delete(String id) {
    // TODO:重构代码
    Optional<User> user = repository.findById(id);
    if (!user.isPresent()) {
      throw new BizException(ExceptionType.USER_NOT_FOUND);
    }
    repository.delete(user.get());
  }

  @Override
  public Page<UserDto> search(Pageable page) {
    return repository.findAll(page).map(userMapper::toDto);
  }

  @Override
  public String createToken(TokenCreateRequest tokenCreateRequest) {
    User user = loadUserByUsername(tokenCreateRequest.getUsername());
    if (!passwordEncoder.matches(tokenCreateRequest.getPassword(), user.getPassword())) {
      throw new BizException(ExceptionType.USER_PASSWORD_NOT_MATCH);
    }
    if (!user.isEnabled()) {
      throw new BizException(ExceptionType.USER_NOT_ENABLED);
    }
    if (!user.isAccountNonLocked()) {
      throw new BizException(ExceptionType.USER_LOCKED);
    }
    return JWT.create()
        .withSubject(user.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION_TIME))
        .sign(Algorithm.HMAC512(SecurityConfig.SECRET.getBytes()));
  }

  @Override
  public UserDto getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User currentUser = loadUserByUsername(authentication.getName());
    return userMapper.toDto(currentUser);
  }

  private void checkUserName(String username) {
    Optional<User> user = repository.findByUsername(username);
    if (user.isPresent()) {
      throw new BizException(ExceptionType.USER_NAME_DUPLICATE);
    }
  }

  @Autowired
  private void setRepository(UserRepository repository) {
    this.repository = repository;
  }

  @Autowired
  private void setUserMapper(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Autowired
  private void setPasswordEncoder(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }
}
