package com.foamfish.spock.example.repository;

import com.foamfish.spock.example.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

    Book findByTitle(String title);
}