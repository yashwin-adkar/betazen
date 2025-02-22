package com.example.controller;

import com.example.model.Admin;
import com.example.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; 
    }

    @PostMapping("/login")
    public String loginAdmin(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             Model model) {
    	Admin admin = adminRepository.findByEmail(email);

    	if (admin != null && admin.getPassword().equals(password)) {
    	    return "redirect:/home";
    	} else {
    	    model.addAttribute("errorMessage", "Invalid Email or Password!");
    	    return "login";
    	}

    }
}
