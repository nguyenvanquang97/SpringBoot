package com.example.demo.repository;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
   private ArrayList<Product> allProduct =new ArrayList<>();

    public ProductRepository() {

    }

    public List<Product> getAllProduct(){return allProduct;}
    public Optional<Product> get(int id){
        return allProduct.stream().filter(c->c.getId()==id).findFirst();
    }
    public Product create(Product product){
        int id;
        if (allProduct.isEmpty()) {
            id = 1;
        } else {
            Product lastProduct = allProduct.get(allProduct.size() - 1);
            id = lastProduct.getId() + 1;
        }
        product.setId(id);
        allProduct.add(product);
        return product;
    }
    public Product edit(Product product){
        get(product.getId()).ifPresent(existProduct->{
            existProduct.setName(product.getName());
            existProduct.setCategory(product.getCategory());
            existProduct.setDetail(product.getDetail());
        });
        return product;
    }
    public void delete(Product product){
        deleteById(product.getId());
    }
    public void deleteById(int id) {
        get(id).ifPresent(existed -> allProduct.remove(existed));
    }

}
