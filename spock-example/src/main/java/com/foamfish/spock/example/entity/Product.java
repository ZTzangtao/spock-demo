package com.foamfish.spock.example.entity;

import lombok.Data;

@Data
public class Product {
    private long id;

    private String name;
    private int price;
    private int weight;
    private int stock;
}
