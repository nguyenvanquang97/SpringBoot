package com.example.api.controller;

import com.example.api.entity.Todo;
import com.example.api.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ToDoController {
    @Autowired
    private ToDoService toDoService;

    @GetMapping("/todos")
    public List<Todo> getTodos(@RequestParam Optional<Boolean> status) {
        if (status.isPresent()) {
            return toDoService.getTodos(status.get());
        }
        return toDoService.getTodos();
    }

    @GetMapping("/todos/{id}")
    public Todo getTodoById(@PathVariable Integer id) {
        return toDoService.getTodoById(id);
    }

    @PostMapping("/todos/{id}")
    public Todo update(@PathVariable Integer id, @RequestBody Todo todo) {

       return toDoService.update(id, todo);
    }
    @PostMapping("/todos/")
    public Todo post( @RequestBody Todo todo) {

        return toDoService.post( todo);
    }
    @DeleteMapping("/todos/{id}")
    public List<Todo> delete(@PathVariable Integer id){
        toDoService.delete(id);
        return toDoService.getTodos();
    }
}

