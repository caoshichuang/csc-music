package com.example.cscmusic.controller;

import com.example.cscmusic.dto.TokenCreateRequest;
import com.example.cscmusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
public class TokenController {

  UserService userService;

  @PostMapping
  public String create(@Validated @RequestBody TokenCreateRequest tokenCreateRequest) {
    return userService.createToken(tokenCreateRequest);
  }

  @Autowired
  private void setUserService(UserService userService) {
    this.userService = userService;
  }
}
