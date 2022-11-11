package com.example.exercisesecurity1.repository;

import com.example.exercisesecurity1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByTaiKhoan(String taiKhoan);


}