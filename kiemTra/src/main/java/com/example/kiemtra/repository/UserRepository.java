package com.example.kiemtra.repository;

import com.example.kiemtra.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}