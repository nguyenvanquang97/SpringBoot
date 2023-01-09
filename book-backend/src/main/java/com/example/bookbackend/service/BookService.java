package com.example.bookbackend.service;

import com.example.bookbackend.entity.Author;
import com.example.bookbackend.entity.Book;
import com.example.bookbackend.entity.Category;
import com.example.bookbackend.exception.NotFoundException;
import com.example.bookbackend.repository.AuthorRepository;
import com.example.bookbackend.repository.BookRepository;
import com.example.bookbackend.repository.CategoryRepository;
import com.example.bookbackend.request.UpsertBookRequest;
import com.github.slugify.Slugify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    private Slugify slugify;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found book with id = " + id);
        });
    }

    public Book createBook(UpsertBookRequest request) {
        // Lấy ra ds category tương ứng (từ ds id gửi lên)
        Set<Category> categories = categoryRepository.findByIdIn(request.getCategoryIds());
        // Lấy ra ds author tương ứng (từ ds id gửi lên)
        Set<Author> authors = authorRepository.findByIdIn(request.getAuthorsIds());
        //tao book
        Book book=Book.builder()
                .title(request.getTitle())
                .slug(slugify.slugify(request.getTitle()))
                .description(request.getDescription())
                .thumbnail(request.getThumbnail())
                .categories(categories)
                .authors(authors)
                .pageNumbers(request.getPageNumber())
                .publishingYear(request.getPublishingYear())
                .price(request.getPrice())
                .build();
        // Trả về book sau khi tạo
        return bookRepository.save(book);
    }

    public Book updateBook(Integer id, UpsertBookRequest request) {
        Book book = bookRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found book with id = " + id);
        });
        // Lấy ra ds category tương ứng (từ ds id gửi lên)
        Set<Category> categories = categoryRepository.findByIdIn(request.getCategoryIds());
        // Lấy ra ds category tương ứng (từ ds id gửi lên)
        Set<Author> authors = authorRepository.findByIdIn(request.getAuthorsIds());

        book.setTitle(request.getTitle());
        book.setSlug(slugify.slugify(request.getTitle()));
        book.setDescription(request.getDescription());
        book.setThumbnail(request.getThumbnail());
        book.setCategories(categories);
        book.setAuthors(authors);
        book.setPrice(request.getPrice());
        book.setPageNumbers(request.getPageNumber());
        book.setPublishingYear(request.getPublishingYear());

        return bookRepository.save(book);
    }
    // 5. Xóa book
    public void deleteBook(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Not found book with id = " + id);
        });
        bookRepository.delete(book);
    }
}
