package com.example.exerciseday02.controller;

import com.example.exerciseday02.repository.BookRepository;
import com.example.exerciseday02.model.Book;
import com.example.exerciseday02.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/home")
public class HomeController {
    ArrayList<Book> allBook=new ArrayList<>();
    BookRepository repo;
    Service service;

    @Autowired
    public HomeController(ArrayList<Book> allBook, BookRepository repo, Service service) {
        this.allBook = allBook;
        this.repo = repo;
        this.service = service;
    }



    public String home(){
        return "Home";
    }
    @GetMapping(value = "/json",produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Book> getBookJson(){

        ArrayList<Book> bookJson=new ArrayList<>();
        bookJson=  repo.getAllBookJson();
        allBook.addAll(bookJson);
        return bookJson;
    }
    @GetMapping(value = "/excel")
    public ArrayList<Book> getBookExcel(){

        ArrayList<Book> bookExcel=new ArrayList<>();
        bookExcel= (ArrayList<Book>) repo.getAllBookExcel();
        System.out.println(bookExcel);
        allBook.addAll(bookExcel);
        return bookExcel;
    }
    @GetMapping("/sortByAuthor")
    public ArrayList<Book> sortByAuthor() {
        service.sortByAuthor(allBook);
        return allBook;
    }
    @GetMapping("/sortByTitle")
    public ArrayList<Book> sortByTitle() {
        service.sortByTitle(allBook);
        return allBook;
    }
    @GetMapping("/sortByYear")
    public ArrayList<Book> sortByYear() {
        service.sortByYear(allBook);
        return allBook;
    }
}
