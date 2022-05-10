package com.example.cscmusic.Mapper;

import com.example.cscmusic.dto.UserDto;
import com.example.cscmusic.entity.User;
import com.example.cscmusic.vo.UserVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    UserDto toDto(User user);

    UserVo toVo(UserDto userDto);
}
