package com.example.cscmusic.dto;

import com.example.cscmusic.enums.Gender;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {

    private String id;

    private String username;

    private String nickname;

    private List<RoleDto> Roles;

    private Gender gender;

    private Boolean locked;

    private Boolean enabled;

    private String lastLoginIp;

    private Date lastLoginTime;
}
