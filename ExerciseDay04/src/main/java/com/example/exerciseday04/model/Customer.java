package com.example.exerciseday04.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Customer {
    private int id;
    private String fullName;
    private String email;
    private Long telephone;

}
