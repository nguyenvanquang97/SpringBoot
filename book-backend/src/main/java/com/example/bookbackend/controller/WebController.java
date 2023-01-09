package com.example.bookbackend.controller;

import com.example.bookbackend.entity.Book;
import com.example.bookbackend.entity.Category;
import com.example.bookbackend.entity.Comment;
import com.example.bookbackend.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class WebController {

    @Autowired
    private WebService webService;


    @GetMapping("/books")
    public List<Book> getBooks(@RequestParam(required = false) String search,
                               @RequestParam(required = false) String category

    ) {
        return webService.getBooks(search, category);
    }

    @GetMapping("/books/{id}/{slug}")
    public Book getBookById(@PathVariable Integer id) {
        return webService.getBookById(id);
    }

    // 3. Lấy ds tất cả category
    @GetMapping("/categories")
    public List<Category> getCategories() {
        return webService.getCategories();
    }
    //lấy sách theo author id
    @GetMapping("/books/author/{authorId}")
    public List<Book> getBooks(@PathVariable List<Integer> authorId){
        return webService.getBookByAuthorId(authorId);
    }
//lấy comment theo book
    @GetMapping("/comments/{bookId}")
    public List<Comment> getCommentByBookId(@PathVariable Integer bookId){
        return webService.getCommentByBookId(bookId);
    }

}