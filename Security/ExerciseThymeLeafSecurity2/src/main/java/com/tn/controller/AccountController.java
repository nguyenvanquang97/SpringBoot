package com.tn.controller;

import com.tn.entity.Account;
import com.tn.entity.Department;
import com.tn.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("account/add")
    public String add() {
        return "account-add";
    }
    @GetMapping("account/list")
    public String list(Model model) {
        List<Account> accounts = accountRepository.findAll();
        model.addAttribute("listAccount", accounts);
        return "account-list";
    }

    @PostMapping("/account/save")
    public String save(@ModelAttribute Account account) throws Exception {

        if (accountRepository.findByUserName(account.getUserName())!=null){
            throw new Exception("UserName đã tồn tại");
        }
        String password= BCrypt.hashpw(account.getPassword(), BCrypt.gensalt(12));
        account.setPassword(password);
        accountRepository.save(account);
        return "redirect:/account/list";
    }
}