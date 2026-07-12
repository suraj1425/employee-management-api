package com.suraj.employeeapi.EmployeeApiApplication.controller;

import com.suraj.employeeapi.EmployeeApiApplication.model.Department;
import com.suraj.employeeapi.EmployeeApiApplication.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {


    DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> getDepartments(){
        return departmentService.findAll();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable int id){
        return departmentService.findById(id);
    }

    @GetMapping({"/withEmployees"})
    public List<Department> getDepartmentWithEmployees(){
        return departmentService.getDepartmentWithEmployees();
    }


    @PostMapping
    public Department addDepartment(@RequestBody Department department ){
        return departmentService.save(department);
    }

    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable int id){

        departmentService.delete(id);
        return "deleted";
    }
}



