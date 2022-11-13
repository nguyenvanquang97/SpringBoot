package com.tn.controller;

import com.tn.entity.Product;
import com.tn.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class productController {
    @Autowired
    private ProductService productService;


    @GetMapping("home")
    public String trangChu(Model model) {
        List<Product> products = productService.getAll();
        System.out.println(
                products
        );
        model.addAttribute("listProduct", products);
        return "index";
    }

    @GetMapping("product-add")
    public String add() {
        return "product-add";
    }

    @PostMapping("/product/save")
    public String save(@ModelAttribute Product product) {
        System.out.println(product);
        productService.add(product);
        return "redirect:/home";
    }


}
