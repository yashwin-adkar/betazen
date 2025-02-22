/*
 * package com.example.model;
 * 
 * import jakarta.persistence.*;
 * 
 * import java.util.Random; import java.util.UUID;
 * 
 * @Entity
 * 
 * @Table(name = "employee_db") public class Employee {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
 * 
 * private String employeeId; private String adminId; private String firstName;
 * private String lastName; private String email; private String phoneNumber;
 * private String role;
 * 
 * public Employee() { this.adminId = generateAdminId(); }
 * 
 * private String generateAdminId() { Random random = new Random(); int
 * adminIdNumber = 1000 + random.nextInt(9000); return "ADM-" + adminIdNumber; }
 * 
 * 
 * public String getEmployeeId() { return employeeId; }
 * 
 * public void setEmployeeId(String employeeId) { this.employeeId = employeeId;
 * }
 * 
 * 
 * public String getAdminId() { return adminId; }
 * 
 * public String getFirstName() { return firstName; }
 * 
 * public void setFirstName(String firstName) { this.firstName = firstName; }
 * 
 * public String getLastName() { return lastName; }
 * 
 * public void setLastName(String lastName) { this.lastName = lastName; }
 * 
 * public String getEmail() { return email; }
 * 
 * public void setEmail(String email) { this.email = email; }
 * 
 * public String getPhoneNumber() { return phoneNumber; }
 * 
 * public void setPhoneNumber(String phoneNumber) { this.phoneNumber =
 * phoneNumber; }
 * 
 * public String getRole() { return role; }
 * 
 * public void setRole(String role) { this.role = role; } }
 */


package com.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Random;

@Entity
@Table(name = "employee_db")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String adminId;

    @NotBlank(message = "First Name is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "First Name must contain only letters")
    private String firstName;

    @NotBlank(message = "Last Name is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last Name must contain only letters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone Number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Phone Number must start with 6-9 and be 10 digits long")
    private String phoneNumber;

    @NotBlank(message = "Role is required")
    private String role;

    public Employee() {
        this.adminId = generateAdminId();
    }

    private String generateAdminId() {
        Random random = new Random();
        int adminIdNumber = 1000 + random.nextInt(9000);
        return "VID-" + adminIdNumber;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

    // Getters and setters
    
}
