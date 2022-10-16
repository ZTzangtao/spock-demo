package com.foamfish.spock.example.controller;

import com.foamfish.spock.example.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    private HelloWorldService service;

    @Autowired
    public HelloWorldController(HelloWorldService service) {
        this.service = service;
    }

    @RequestMapping("/")
    public String hello() {
        return service.getHelloMessage();
    }

}
