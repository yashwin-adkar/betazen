package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactUsController {
    
    @GetMapping("/contactus")
    public String contactUsPage() {
        return "contactus"; // return the name of the contactus view (contactus.html, contactus.jsp, etc.)
    }
}
