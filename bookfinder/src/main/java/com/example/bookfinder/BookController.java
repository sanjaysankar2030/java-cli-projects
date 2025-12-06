package com.example.bookfinder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class BookController {
    @GetMapping("/book")
    public String getbook(){
        return "Hello from rest controller";
    }

}