package com.example.exercisesecurity1.config;


import com.example.exercisesecurity1.service.InterAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class securityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private InterAccountService interAccountService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(interAccountService).passwordEncoder(new BCryptPasswordEncoder());
    }

}