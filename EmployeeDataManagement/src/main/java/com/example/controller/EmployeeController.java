/*
 * package com.example.controller;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.*;
 * 
 * import com.example.model.Employee; import
 * com.example.repository.EmployeeRepository;
 * 
 * @Controller public class EmployeeController {
 * 
 * @Autowired private EmployeeRepository employeeRepository;
 * 
 * @GetMapping("/register") public String showRegistrationForm(Model model) {
 * model.addAttribute("employee", new Employee()); return "register"; }
 * 
 * @PostMapping("/register") public String registerEmployee(@ModelAttribute
 * Employee employee, Model model) {
 * 
 * if (employeeRepository.existsByEmployeeId(employee.getEmployeeId())) {
 * model.addAttribute("errorMessage",
 * "Employee ID already exists! Please use a different ID."); return "register";
 * }
 * 
 * if (employeeRepository.existsByEmail(employee.getEmail())) {
 * model.addAttribute("errorMessage",
 * "Email ID already exists! Please use a different email."); return "register";
 * }
 * 
 * employeeRepository.save(employee);
 * 
 * model.addAttribute("successMessage", "Employee Registered Successfully!");
 * 
 * return "register"; }
 * 
 * 
 * @GetMapping("/home") public String showHomePage(Model model) { List<Employee>
 * employees = employeeRepository.findAll(); model.addAttribute("employees",
 * employees); return "home"; }
 * 
 * }
 */



package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

import jakarta.validation.Valid;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "register";
    }

	/*
	 * @PostMapping("/register") public String
	 * registerEmployee(@Valid @ModelAttribute Employee employee, BindingResult
	 * bindingResult, Model model) { if (bindingResult.hasErrors()) { return
	 * "register"; }
	 * 
	 * if (employeeRepository.existsByEmail(employee.getEmail())) {
	 * model.addAttribute("errorMessage",
	 * "Email ID already exists! Please use a different email."); return "register";
	 * }
	 * 
	 * employeeRepository.save(employee); model.addAttribute("successMessage",
	 * "Employee Registered Successfully!"); return "register"; }
	 */
    @PostMapping("/register")
    public String registerEmployee(@Valid @ModelAttribute Employee employee, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (employeeRepository.existsByEmail(employee.getEmail())) {
            model.addAttribute("errorMessage", "Email already exists! Use a different email.");
            return "register";
        }

        employeeRepository.save(employee);
        model.addAttribute("successMessage", "Employee Registered Successfully!");
        return "register";
    }

    @GetMapping("/home")
    public String showHomePage(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "home";
    }
}
