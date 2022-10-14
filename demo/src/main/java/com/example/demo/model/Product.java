package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private  String name;
    private  Category category;
    private  String detail;
    private MultipartFile photo;
}
