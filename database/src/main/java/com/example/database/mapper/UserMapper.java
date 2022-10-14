package com.example.database.mapper;

import com.example.database.dto.UserDto;
import com.example.database.entity.User;

public class UserMapper {
    public static UserDto toUserDto(User user){
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }
}
