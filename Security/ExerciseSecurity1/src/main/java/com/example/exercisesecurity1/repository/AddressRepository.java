package com.example.exercisesecurity1.repository;

import com.example.exercisesecurity1.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}