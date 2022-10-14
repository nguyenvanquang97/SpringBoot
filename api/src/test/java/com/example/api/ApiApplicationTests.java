package com.example.api;

import com.example.api.entity.Todo;
import com.example.api.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace =AutoConfigureTestDatabase.Replace.NONE)
class ApiApplicationTests {

    @Autowired
    private TodoRepository todoRepository;
    @Test
    @Rollback(value = false)
    void save_todo() {
       Todo todo1=Todo.builder().title("lam bai tap").build();
        Todo todo2=Todo.builder().title("di choi").status(true).build();
        Todo todo3=Todo.builder().title("da bong").build();

        todoRepository.saveAll(List.of(todo1,todo2,todo3));

    }



}
