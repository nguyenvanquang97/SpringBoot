package com.tn.controller;

import com.tn.entity.Account;
import com.tn.entity.Product;
import com.tn.repository.AccountRepository;
import com.tn.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class accountController {
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("account-add")
    public String add() {
        return "account-add";
    }

    @PostMapping("/account/save")
    public String savea(@ModelAttribute Account account) {
       String password= BCrypt.hashpw(account.getPassword(), BCrypt.gensalt(12));
       account.setPassword(password);
        accountRepository.save(account);
        return "redirect:/home";
    }
}
