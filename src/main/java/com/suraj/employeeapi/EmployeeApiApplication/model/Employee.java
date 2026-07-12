package com.suraj.employeeapi.EmployeeApiApplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.*;




@Entity
@Table(name= "Employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private double salary;

    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @JsonBackReference
    private Department department;


    public Employee() {

    }

    public Employee(String name, double salary, String email, Department department) {
        this.name = name;
        this.salary = salary;
        this.email = email;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
