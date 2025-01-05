package com.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/home")
    public String home(Model model) {
        // sending data to view
        model.addAttribute("name", "Siddharth Tiwari");
        model.addAttribute("githubRepo", "https://github.com/siddharthkt45");
        return "home";
    }

    // about route
    @RequestMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", true);
        System.out.println("About page loading...");
        return "about";
    }

    // services route
    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("Services page loading...");
        return "services";
    }
}
