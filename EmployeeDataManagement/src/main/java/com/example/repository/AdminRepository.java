package com.example.repository;

import com.example.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    Admin findByResetToken(String resetToken); 
    Admin findByEmail(String email);
    Admin findByPassword(String password); // Find admin by password
}
