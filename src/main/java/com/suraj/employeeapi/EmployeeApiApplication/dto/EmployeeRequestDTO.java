package com.suraj.employeeapi.EmployeeApiApplication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class EmployeeRequestDTO {

    @NotBlank(message = "Employee name is required ")
    private String name;

    @Email(message = "Invalid Email format")
    private String email;

    @Min(value = 10000 , message = "Salary must be greater than 10000")
    private double salary;

    private int departmentId;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getSalary() {
        return salary;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
