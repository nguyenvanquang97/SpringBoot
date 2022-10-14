package com.example.api.service;

import com.example.api.entity.Todo;
import com.example.api.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getTodos(){
        return todoRepository.findAll();
    }
    public List<Todo> getTodos(Boolean status){
        return todoRepository.getTodosByStatus(status);
    }
    public Todo getTodoById(Integer id){
        Optional<Todo> todo= todoRepository.findById(id);
        if (todo.isPresent()){
            return todo.get();
        }throw new RuntimeException("Not found todo with id="+id);
    }

    public Todo update(Integer id,Todo saveTodo) {

        Optional<Todo> todo =    todoRepository
                .findById(id);

        if(todo.isPresent()) {
            todo.get().setTitle(saveTodo.getTitle());
            todo.get().setStatus(saveTodo.getStatus());

        }
        return todoRepository.save(todo.get());
    }
    public Todo post(Todo todo){
        return todoRepository.save(todo);
    }
    public void delete(Integer id){
         todoRepository.deleteById(id);
    }
}
