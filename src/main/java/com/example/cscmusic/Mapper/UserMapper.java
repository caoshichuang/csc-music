package com.example.cscmusic.Mapper;


import com.example.cscmusic.dto.UserCreateRequest;
import com.example.cscmusic.dto.UserDto;
import com.example.cscmusic.dto.UserUpdateRequest;
import com.example.cscmusic.entity.User;
import com.example.cscmusic.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * @author caoshichuang
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    UserVo toVo(UserDto userDto);

    User createEntity(UserCreateRequest userCreateRequest);

    User updateEntity(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
