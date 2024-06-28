package org.soap.restapiexample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/api")
    public class MyController {

        @GetMapping("/hello")
        public String sayHello() {
            return "Hello, World!";
        }

        @PostMapping("/echo")
        public String echo(@RequestBody String message) {
            return "Echo: " + message;
        }
    }


