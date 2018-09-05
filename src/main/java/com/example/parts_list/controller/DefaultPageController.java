package com.example.parts_list.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultPageController {
    @GetMapping("/")
    public String startPage(){
        return "startPage";
    }
}
