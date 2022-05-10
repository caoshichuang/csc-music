package com.example.cscmusic.dto;

import com.example.cscmusic.vo.RoleVo;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private String id;

    private String username;

    private String nickname;

    private List<RoleVo> Roles;
}
