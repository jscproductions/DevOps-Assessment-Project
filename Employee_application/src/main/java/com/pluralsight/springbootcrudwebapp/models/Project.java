package com.pluralsight.springbootcrudwebapp.models;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;


@Entity

@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="manager_id")
    private Long managerId;

   // @ManyToOne
   // @JoinColumn(name = "emp_id")
   // private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
}
