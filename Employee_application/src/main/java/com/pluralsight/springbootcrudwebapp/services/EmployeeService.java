package com.pluralsight.springbootcrudwebapp.services;

import com.pluralsight.springbootcrudwebapp.models.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    void deleteEmployeeById(long id);
    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
