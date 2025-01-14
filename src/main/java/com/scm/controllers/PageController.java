package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    // home route
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

    // contact route
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    // login route
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    // register route
    @GetMapping("/register")
    public String register(Model model) {
        UserForm userForm = new UserForm();
        // can add default data too like this - (this will display the name in the name field by default)
        // userForm.setName("Siddharth");
        model.addAttribute("userForm", userForm);
        return "register";
    }

    // do-register route
    @PostMapping("/do-register")
    public String processRegister(@ModelAttribute UserForm userForm, HttpSession session) {
        System.out.println("Processing registration...");

        // fetch form data
        System.out.println(userForm);

        // UserForm class

        // validate form data
        
        // map the UserForm to User entity --- commenting the below code since it doesn't pass default values
        // User user = User.builder()
        //                 .name(userForm.getName())
        //                 .email(userForm.getEmail())
        //                 .password(userForm.getPassword())
        //                 .phoneNumber(userForm.getPhoneNumber())
        //                 .about(userForm.getAbout())
        //                 .build();

        // map the UserForm to User entity --- by directly passing values to setter methods

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setAbout(userForm.getAbout());

        // save data to database
        User savedUser = userService.saveUser(user);

        System.out.println("User saved: " + savedUser);

        // message = "Registration successful"
        // Message message = 

        session.setAttribute("message", "Registration Successful");
        
        // redirect to login page
        return "redirect:/register";
    }

}
