package com.foamfish.spock.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String title;

    protected Book() {
        // no-args JPA constructor
    }

    public Book(String title) {
        this.title = title;
    }
}