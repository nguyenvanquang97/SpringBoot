package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class Controller {
    private final ProductRepository productRepository;

    public Controller(){
        productRepository = new ProductRepository();
    }
    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/listAll")
    public String listAll(Model model) {


        model.addAttribute("products", productRepository.getAllProduct());
        return "listAll";
    }
    @GetMapping("/listAll/{id}")
    public String detail(@PathVariable("id") Integer id, Model model){
        Product product = productRepository.get(id).get();
        model.addAttribute("product",product);
        return "detail";
    }
    @GetMapping("/product/edit/{id}")
    public String editCustomer(@PathVariable("id") int id, Model model) {
        Optional<Product> product = productRepository.get(id);
        if(product.isPresent()){
            model.addAttribute("product",product);
        }
        return "productForm";
    }



}
