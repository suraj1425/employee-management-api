package com.suraj.employeeapi.EmployeeApiApplication.dto;

public class EmployeeResponseDTO {

    private int id;
    private String name;
    private String email;
    private String departmentName;

    public EmployeeResponseDTO(int id, String name, String email ,String departmentName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.departmentName = departmentName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
