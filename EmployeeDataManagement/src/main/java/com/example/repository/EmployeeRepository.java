package com.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	/* boolean existsByEmployeeId(String employeeId); */
    boolean existsByEmail(String email);
    boolean existsByFirstNameAndLastName(String firstName, String lastName);

}
