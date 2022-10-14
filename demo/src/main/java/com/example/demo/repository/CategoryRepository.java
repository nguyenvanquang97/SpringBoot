package com.example.demo.repository;

import com.example.demo.model.Category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryRepository {
    private List<Category> categories=new ArrayList<>();

    public CategoryRepository() {
        List<Category> list= Arrays.asList(
                new Category(1,"Iphone"),
                new Category(2,"Samsung"),
                new Category(3,"Xiaomi")
        );
        list.forEach(c->categories.add(c));
    }
}
