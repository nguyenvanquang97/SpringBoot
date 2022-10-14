package com.example.database;

import com.example.database.UserRepository.UserRepository;
import com.example.database.dto.UserDto;
import com.example.database.dto.UserInfo;
import com.example.database.entity.User;
import com.example.database.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserDtoTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void  test_getUserDto(){
        User user=userRepository.getUserById(10);
        UserDto userDto= UserMapper.toUserDto(user);
        System.out.println(userDto);
    }
    @Test
    void test_getUserByEmail(){
        UserDto userDto=userRepository.getUserDtoByEmail("jeana.oreilly@gmail.com");
        System.out.println(userDto);
    }

    @Test
    void test_getUserInfoByEmail(){
        UserInfo userInfo=userRepository.getUserInfoByEmail("jeana.oreilly@gmail.com");
        System.out.println(userInfo.getId()+"-"+userInfo.getName());
    }
}
