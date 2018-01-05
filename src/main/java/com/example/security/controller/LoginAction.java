package com.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginAction {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("home")
    public String home() {
        return "home";
    }

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

}
