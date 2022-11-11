package com.example.exercisesecurity1.service;


import com.example.exercisesecurity1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;

@Service
public class AccountService implements InterAccountService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String taiKhoan) throws UsernameNotFoundException {
        com.example.exercisesecurity1.entity.User account=userRepository.findByTaiKhoan(taiKhoan);

        if (account == null){
            throw new EntityNotFoundException("user or password not found");
        }

        String matKhau = account.getMatKhau();

        return  new User(taiKhoan,matKhau, Collections.emptyList());
    }
}