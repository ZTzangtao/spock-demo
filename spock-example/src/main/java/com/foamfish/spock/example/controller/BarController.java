package com.foamfish.spock.example.controller;

import com.foamfish.spock.example.dto.Hello;
import com.foamfish.spock.example.service.BarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = "application/json")
public class BarController {

    private final BarService service;

    public BarController(BarService service) {
        this.service = service;
    }

    @GetMapping("/bar/{name}")
    public Hello helloByName(@PathVariable String name) {
        return service.hello(name);
    }
}
