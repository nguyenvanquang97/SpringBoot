package com.example.dich;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafArrayController {
    @GetMapping({"/"})
    public String home() {
        return "home";
    }
//    @GetMapping("/arrays")
//    public String arrayController(Model model) {
//        String[] continents = {
//                "Africa", "Antarctica", "Asia", "Australia",
//                "Europe", "North America", "Sourth America"
//        };
//
//        model.addAttribute("continents", continents);
//
//        return "continents";
//    }
}