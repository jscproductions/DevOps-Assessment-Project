package com.pluralsight.springbootcrudwebapp.services;

import com.pluralsight.springbootcrudwebapp.models.Employee;
import com.pluralsight.springbootcrudwebapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    public void saveEmployee(Employee employee){
        this.employeeRepository.save(employee);
    }
    public Employee getEmployeeById(Long id){
        Optional <Employee> optional= employeeRepository.findById(id);
        Employee employee=null;
        if(optional.isPresent()){
            employee=optional.get();
        }
        else{
            throw new RuntimeException("No employee with the id "+id+"exist.");
        }
        return employee;
    }
    public void deleteEmployeeById(long id){
        this.employeeRepository.deleteById(id);
    }
    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.employeeRepository.findAll(pageable);
    }
}
