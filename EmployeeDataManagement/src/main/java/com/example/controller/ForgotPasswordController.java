package com.example.controller;

import com.example.model.Admin;
import com.example.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class ForgotPasswordController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam("email") String email, Model model) {
        Admin admin = adminRepository.findByEmail(email);
        
        if (admin == null) {
            model.addAttribute("errorMessage", "No account found with that email!");
            return "forgot-password";
        }

        // Generate reset token
        String token = UUID.randomUUID().toString();
        admin.setResetToken(token);
        adminRepository.save(admin);

        // Send email with reset link
        String resetUrl = "http://localhost:8081/reset-password?token=" + token + "&email=" + email;

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(admin.getEmail());
        mailMessage.setSubject("Password Reset Request");
        mailMessage.setText("Click the link below to reset your password:\n" + resetUrl);
        mailSender.send(mailMessage);

        model.addAttribute("successMessage", "Password reset link sent to your email.");
        return "forgot-password";
    }

    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam("token") String token, 
                                        @RequestParam("email") String email, 
                                        Model model) {
        Admin admin = adminRepository.findByResetToken(token);
        if (admin == null || !admin.getEmail().equals(email)) {
            model.addAttribute("errorMessage", "Invalid or expired reset token!");
            return "forgot-password";
        }
        model.addAttribute("token", token);
        model.addAttribute("email", email);
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String processResetPassword(@RequestParam("token") String token,
                                       @RequestParam("email") String email,
                                       @RequestParam("password") String password,
                                       @RequestParam("confirmPassword") String confirmPassword,
                                       Model model) {
        Admin admin = adminRepository.findByResetToken(token);
        if (admin == null || !admin.getEmail().equals(email)) {
            model.addAttribute("errorMessage", "Invalid or expired token!");
            return "reset-password";
        }

        if (!password.equals(confirmPassword)) {
            model.addAttribute("errorMessage", "Passwords do not match!");
            return "reset-password";
        }

        // Update password and clear reset token
        admin.setPassword(password);
        admin.setResetToken(null);
        adminRepository.save(admin);

        model.addAttribute("successMessage", "Password reset successful! You can now log in.");
        return "reset-password";
    }
}
