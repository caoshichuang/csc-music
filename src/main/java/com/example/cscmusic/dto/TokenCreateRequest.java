package com.example.cscmusic.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class TokenCreateRequest {
  @NotBlank(message = "用户名不能为空")
  @Size(min = 4, max = 64, message = "用户名长度为4到64字符之间")
  private String username;

  @NotBlank(message = "密码不能为空")
  @Size(min = 4, max = 64, message = "密码长度为4到64字符之间")
  private String password;
}