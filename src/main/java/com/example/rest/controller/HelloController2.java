package com.example.rest.controller;
//Attention : ce package ne sera pas scané par @SpringBootApplication

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController2 {
    @RequestMapping(value = {"/hello2", "/h2"})
    public String hello() {
        System.out.println("ce package sera scané par @SpringBootApplication ");
        return "Hello World From my first API @RestControler ";
    }
}
