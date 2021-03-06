package com.example.cscmusic.controller;

import com.example.cscmusic.Mapper.UserMapper;
import com.example.cscmusic.dto.UserCreateRequest;
import com.example.cscmusic.dto.UserUpdateRequest;
import com.example.cscmusic.service.UserService;
import com.example.cscmusic.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author caoshichuang
 */
@RestController
@RequestMapping("/users")
@CrossOrigin
@Api(tags = "用户")
public class UserController {

  UserService userService;
  UserMapper userMapper;

  @GetMapping("/{id}")
  UserVo get(@PathVariable String id) {
    return userMapper.toVo(userService.get(id));
  }

  @PutMapping("/{id}")
  UserVo update(
      @PathVariable String id, @Validated @RequestBody UserUpdateRequest userUpdateRequest) {
    return userMapper.toVo(userService.update(id, userUpdateRequest));
  }

  @DeleteMapping("/{id}")
  void delete(@PathVariable String id) {
    userService.delete(id);
  }

  @GetMapping
  @ApiOperation("用户检索")
  Page<UserVo> search(
      @PageableDefault(
              sort = {"createdTime"},
              direction = Sort.Direction.ASC)
          Pageable pagebale) {
    return userService.search(pagebale).map(userMapper::toVo);
  }

  @PostMapping
  UserVo create(@Validated @RequestBody UserCreateRequest userCreateRequest) {
    return userMapper.toVo(userService.create(userCreateRequest));
  }

  @GetMapping("/me")
  UserVo me() {
    return userMapper.toVo(userService.getCurrentUser());
  }

  @Autowired
  private void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Autowired
  private void setUserMapper(UserMapper userMapper) {
    this.userMapper = userMapper;
  }
}
