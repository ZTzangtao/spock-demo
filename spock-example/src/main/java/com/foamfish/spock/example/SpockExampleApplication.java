package com.foamfish.spock.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SpockExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpockExampleApplication.class, args);
    }

}
