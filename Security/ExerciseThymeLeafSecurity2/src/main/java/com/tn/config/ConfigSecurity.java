package com.tn.config;

import com.tn.service.InterAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private InterAccountService interAccountService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(interAccountService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .antMatchers("/department/list").permitAll()
                .antMatchers("department/save").hasAnyAuthority("ADMIN","EMPLOYEE")
                .antMatchers("/account/add").hasAnyAuthority("ADMIN")
                .antMatchers("/account/list").hasAnyAuthority("ADMIN","EMPLOYEE")
                .anyRequest().authenticated()
                .and().formLogin();
    }
}