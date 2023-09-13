package com.pluralsight.springbootcrudwebapp.controller;


import com.pluralsight.springbootcrudwebapp.models.Employee;
import com.pluralsight.springbootcrudwebapp.models.Project;
import com.pluralsight.springbootcrudwebapp.repositories.EmployeeRepository;
import com.pluralsight.springbootcrudwebapp.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Project> listall(){return projectRepository.findAll();}

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody Project project){
        Long mid=project.getManagerId();
        Optional<Employee> manager = employeeRepository.findById(mid);

        if (manager.isPresent()) {
            // Manager exists, save the project
            Project savedProject=projectRepository.save(project);
            return ResponseEntity.ok("Project created with title: " + savedProject.getTitle());
        } else {
            // Manager does not exist, return an error response
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Manager with ID " + mid + " does not exist.");
        }

    }
}
