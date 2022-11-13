package com.tn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController {
    @GetMapping("category/list")
    public String getAll(){
        return "category-list";
    }
    @GetMapping("admin/save")
    public String save(){
        return "category-save";
    }
}
