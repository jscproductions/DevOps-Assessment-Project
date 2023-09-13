package com.pluralsight.springbootcrudwebapp.repositories;

import com.pluralsight.springbootcrudwebapp.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
