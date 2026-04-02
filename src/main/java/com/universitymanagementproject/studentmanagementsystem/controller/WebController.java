package com.universitymanagementproject.studentmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    //@GetMapping("/")
    //public String index() {
        // This will redirect authenticated users to the student list by default
       // return "redirect:/students";
}
//}