package com.example.exercisesecurity1.controller;

import com.example.exercisesecurity1.entity.User;
import com.example.exercisesecurity1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

@GetMapping()
    public ResponseEntity<?> getAll(){
        List<User> userList=userRepository.findAll();
        return ResponseEntity.ok(userList);
    }
}
