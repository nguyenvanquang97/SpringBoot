package com.tn.service;


import com.tn.repository.ProductRepository;
import com.tn.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getAll(){
        return productRepository.findAll();
    }
    public Product add(Product product){
        return productRepository.save(product);
    }

}
