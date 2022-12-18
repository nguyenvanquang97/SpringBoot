package com.example.testapi;

import com.example.testapi.Entity.Employee;
import com.example.testapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    private EmployeeRepository employeeRepository;


    @GetMapping("/{firstName}")
    public Employee findByFirstName(@PathVariable String firstName){
        return employeeRepository.findByFirstName(firstName);
    }
    @GetMapping("/list")
    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }


}
