package com.example.exerciseday02.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.exerciseday02.model.Book;
import com.poiji.bind.Poiji;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Repository
public class BookRepository {
    private ArrayList<Book> bookJson = new ArrayList<>();

    public BookRepository() {
        try {
            File file = ResourceUtils.getFile("classpath:static/book.json");
            ObjectMapper mapper = new ObjectMapper();
            bookJson.addAll(mapper.readValue(file, new TypeReference<List<Book>>() {
            }));
            bookJson.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public ArrayList<Book> getAllBookJson() {
        return bookJson;
    }

    public List<Book> getAllBookExcel() {
        try {
            File file = ResourceUtils.getFile("classpath:static/book.xlsx");
            List<Book> listBook = Poiji.fromExcel(file, Book.class);
            return listBook;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}

