package com.example.bookbackend.repository;

import com.example.bookbackend.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("""
            select b from Book b inner join b.categories category
            where (:term is null or upper(b.title) like upper(concat('%', :term, '%')))  and (:categoryName is null or upper(category.name) = upper(:categoryName))
            group by b.id
            order by b.publishingYear asc
            """)
    List<Book> findByTitleContainsIgnoreCaseAndCategories_NameIgnoreCase(@Param("term") String term, @Param("categoryName") String categoryName);

    List<Book> findByAuthors_IdIn(Collection<Integer> ids);



}