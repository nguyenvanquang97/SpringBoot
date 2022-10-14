package com.example.database;


import com.example.database.UserRepository.UserRepository;
import com.example.database.entity.User;
import com.github.javafaker.Faker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DatabaseApplicationTests {
@Autowired
private UserRepository userRepository;
    @Test
    @Rollback(value = false)
    void save_user() {
        Faker faker=new Faker();
        for (int i=0;i<50;i++){
            User user=User.builder()
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .age(faker.number().numberBetween(20,70))
                    .build();

            userRepository.save(user);
        }
    }
    @Test
    void getAllUser(){
        List<User> users=userRepository.findAll();
        System.out.println(users.size());
    }
@Test
    void getUserById(){
    Optional<User> userOptional=userRepository.findById(1);
    userOptional.ifPresent(System.out::println);
}
@Test
void test_getByNameContainsIgnoreCase(){
        List<User> users=userRepository.getByNameContainsIgnoreCase("Chong");
        users.forEach(System.out::println);

    Assertions.assertThat(users).isNotNull();
    Assertions.assertThat((users.size())).isEqualTo( 1);
}
@Test
@Rollback(value = false)
    void deleteUserById(){
        userRepository.deleteById(1);
}

@Test
    void test_pagination(){
       Page<User> page= userRepository.findAll(PageRequest.of(0,10, Sort.by("age").ascending()));
    page.getContent().forEach(System.out::println);
}
}

