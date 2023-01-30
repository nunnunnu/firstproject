package com.green.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
    @GetMapping("/")
    public String getIndex(HttpSession session){
        if(session.getAttribute("loginUser") != null){
            return "redirect:/main";
        }
        return "/index";
    }
    @GetMapping("/main")
    public String getMain(){
        return "/main";
    }
}
