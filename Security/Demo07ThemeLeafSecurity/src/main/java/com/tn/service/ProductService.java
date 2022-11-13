package com.tn.service;


import com.tn.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();
    Product add(Product product);
}
