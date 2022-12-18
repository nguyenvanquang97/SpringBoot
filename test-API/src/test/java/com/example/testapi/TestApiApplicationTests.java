package com.example.testapi;

import com.example.testapi.Dto.UserDto;
import com.example.testapi.Entity.*;
import com.example.testapi.repository.*;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.*;

@SpringBootTest
class TestApiApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private Faker faker;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private Random random;
    @Test
    void saveBlog() {
        for (int i = 0; i < 10; i++) {
            Blog blog=new Blog();
            blog.setTitle("title"+i);
            blogRepository.save(blog);
        }
    }
    @Test
    void deleteCategory() {
     Optional<Category> category= categoryRepository.findById(6);
     categoryRepository.delete(category.get());
    }
    @Test
    void saveTag(){
        for (int i = 0; i <10 ; i++) {
            Tag newTag=new Tag();
            newTag.setName(faker.leagueOfLegends().champion());
            tagRepository.save(newTag);
        }
    }

    @Test
    void saveCategory(){
        for (int i = 0; i <10 ; i++) {
            Category category=new Category();
            category.setName(faker.animal().name());
            categoryRepository.save(category);
        }
    }
    @Test
    void saveProduct(){
        List<Tag> tags=tagRepository.findAll();
        List<Category> categories=categoryRepository.findAll();
        for (int i = 0; i <20; i++) {

            Set<Tag> tagSet=new LinkedHashSet<>();

            for (int j = 0; j <3 ; j++) {
               Tag newTag= tags.get(random.nextInt(tags.size()));
               tagSet.add(newTag);
            }

            Product product=Product.builder()
                    .name(faker.funnyName().name())
                    .tags(tagSet)
                    .category(categories.get(random.nextInt(categories.size())))
                    .build();
            productRepository.save(product);
        }
    }


    @Test
    void saveEmoloyee() {
        for (int i = 0; i < 50; i++) {
            Employee newEmployee=Employee.builder()
                    .emailAddress(faker.internet().emailAddress())
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .build();
            employeeRepository.save(newEmployee);
        }
    }

    @Test
    void sortByFirstName() {
        List<Employee> employees = employeeRepository.findByFirstNameContains("a", Sort.by("firstName"));
        employees.forEach(System.out::println);
    }
    @Test
    void findByLastNameContains() {
        Page<Employee> pages = employeeRepository.findByLastNameContains("er", PageRequest.of(1,10));
        pages.getContent().forEach(System.out::println);
        System.out.println(pages.getTotalPages());
        System.out.println(pages.getTotalElements());
    }
    @Test
    void  saveUser(){
        for (int i = 0; i <30 ; i++) {
            User user=User.builder()
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .password("123")
                    .build();
        userRepository.save(user);
        }
    }
    @Test
    void  findUserByName(){
        List<UserRepository.UserDto> userDtos=userRepository.findByNameContains("a");
        userDtos.forEach(System.out::println);
    }
}
