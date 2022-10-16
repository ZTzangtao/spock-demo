package com.foamfish.spock.example.controller;

import com.foamfish.spock.example.dto.Hello;
import com.foamfish.spock.example.service.BarService;
import com.foamfish.spock.example.service.FooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = "application/json")
public class FooController {

    @Autowired
    private FooService service;

    @GetMapping("/foo")
    public Hello helloByName() {
        return service.hello();
    }
}
