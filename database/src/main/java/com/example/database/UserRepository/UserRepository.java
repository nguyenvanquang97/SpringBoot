package com.example.database.UserRepository;

import com.example.database.dto.UserDto;
import com.example.database.dto.UserInfo;
import com.example.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository <User,Integer>{
//       cách 1:method
        List<User> getByNameContainsIgnoreCase(String name);
        boolean existsByEmailIgnoreCase(String email);
        void deleteByEmail(String email);
        User getUserById(Integer id);

        //cách 2:JPA Query Language
        @Query("select  u from User u where u.id=:id")
        User getUserDetailById(@Param("id") Integer id);

        @Query(nativeQuery = true,value = "select * from user where id=?1")
        User getUserByIdUsingNativeQuery(Integer id);


        //thay đổi thông tin user
        @Transactional
        @Modifying
        @Query("update User u set u.name=?2 where u.id =?1")
        void updateUserName(Integer id,String name);

        @Query("select new com.example.database.dto.UserDto(u.id,u.name,u.email) from User u where u.email=?1")
        UserDto getUserDtoByEmail(String email);

        UserInfo getUserInfoByEmail(String email);
        }


