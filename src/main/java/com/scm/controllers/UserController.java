package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    // user dashboard route
    @GetMapping("/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }

    // user profile route
    @GetMapping("/profile")
    public String userProfile() {
        return "user/profile";
    }

    // user add contact route


    // user view contacts route

    // user edit contact route

    // user delete contact route

    // user search contact route
    
}
