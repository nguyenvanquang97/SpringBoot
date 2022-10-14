package com.example.database.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name ="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="email",unique = true,nullable = false)
    private String email;
    @Column(name="age")
    private Integer age;
    @Column(name="birth")
    private LocalDate birth;

    @PrePersist
    public void prePersist() {
        birth =LocalDate.now().
                minusYears(age);
    }
}
