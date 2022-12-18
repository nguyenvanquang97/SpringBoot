package com.example.testapi.repository;

import com.example.testapi.Entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByEmailAddressAndLastName(String emailAddress, String lastName);

    List<Employee> findByLastNameOrderByFirstNameAsc(String lastName);

    Set<Employee> findByFirstNameOrLastName(String firstName, String lastName);

    List<Employee> findByFirstNameIgnoreCase(String firstName);

    @Query("select employee from Employee employee where employee.firstName = ?1")
    Employee findByFirstName(String firstName);

    List<Employee> findByFirstNameContains(String firstName, Sort sort);
    Page<Employee> findByLastNameContains(String lastName, Pageable pageable);

}