package com.example.kiemtra.repository;

import com.example.kiemtra.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
    List<Topic> findByNameContains(String name);

}