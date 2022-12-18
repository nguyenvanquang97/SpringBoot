package com.example.testapi.repository;

import com.example.testapi.Dto.UserDto;
import com.example.testapi.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select u.id,u.name,u.email from User u where u.name like concat('%', ?1, '%')",nativeQuery = true)
    List< UserDto  > findByNameContains(String name);


}