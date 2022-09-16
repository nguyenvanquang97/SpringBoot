package com.example.exerciseday04.controller;

import com.example.exerciseday04.model.Customer;
import com.example.exerciseday04.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class Controller {
    private Repository repository;

    public Controller(){
        repository = new Repository();
    }
    @GetMapping("/")
    public String home() {
        return "home";
    }
    @GetMapping("/listAll")
    public String listAll(Model model) {
        List<Customer> customers = repository.getAllCustomer();

        model.addAttribute("people", customers);
        return "listAll";
    }

    @GetMapping("/listAll/{id}")
    public String detail(@PathVariable("id") Integer id, Model model){
        Customer customer = repository.get(id).get();
        model.addAttribute("customer",customer);
        return "detail";
    }

    @GetMapping("/customer/edit/{id}")
    public String editCustomer(@PathVariable("id") int id, Model model) {
        Optional<Customer> customer = repository.get(id);
        if(customer.isPresent()){
            model.addAttribute("customer",customer);
        }
        return "customerForm";
    }

    @PostMapping("/post")
    public String postInfo(@ModelAttribute("person") Customer customer, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            if(customer.getId()>0){
                repository.edit(customer);
            }else{
                repository.create(customer);
            }
            model.addAttribute("people", repository.getAllCustomer());
            return "redirect:/listAll";
        }
        return "customerForm";
    }

    @GetMapping("/create")
    public String showForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customerForm";
    }

    @GetMapping("/customer/delete/{id}")
    public String deleteCustomer(@PathVariable("id") int id, Model model){
        repository.deleteById(id);
        model.addAttribute("people",repository.getAllCustomer());
        return "redirect:/listAll";
    }

    @GetMapping("/search")
    public String searchCustomer(HttpServletRequest request, Model model){
        String email = request.getParameter("email");
        if(Objects.equals(email, "")){
            model.addAttribute("people",repository.getAllCustomer());
            return "redirect:/listAll";
        }
        else{
            Customer customer = repository.search(email);
            model.addAttribute("people",customer);
            return "listAll";
        }
    }
    @GetMapping("/sort/{direction}")
    public String sortByFullName(@PathVariable("direction") String direction,Model model){
        repository.sortByFullName(direction);
        ArrayList<Customer> customers=repository.getAllCustomer();
        model.addAttribute("people",customers);
        return "listAll";
    }



}
