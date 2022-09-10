package com.example.exerciseday02.service;

import com.example.exerciseday02.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;

@org.springframework.stereotype.Service
public class Service {
    public void sortByAuthor(ArrayList<Book> book) {
        book.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getAuthor().compareTo(o2.getAuthor());
            }
        });
    }
    public void sortByTitle(ArrayList<Book> book){
        book.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
    }

    public void sortByYear(ArrayList<Book> book) {
        book.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getYear() - o2.getYear();
            }
        });
    }
}
