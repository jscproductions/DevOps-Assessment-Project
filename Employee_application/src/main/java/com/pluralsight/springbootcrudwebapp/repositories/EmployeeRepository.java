package com.pluralsight.springbootcrudwebapp.repositories;

import com.pluralsight.springbootcrudwebapp.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
