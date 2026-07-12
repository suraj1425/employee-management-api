package com.suraj.employeeapi.EmployeeApiApplication.service;

import com.suraj.employeeapi.EmployeeApiApplication.Exception.DepartmentNotFoundException;
import com.suraj.employeeapi.EmployeeApiApplication.model.Department;
import com.suraj.employeeapi.EmployeeApiApplication.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }


    public Department save(Department department){
        return departmentRepository.save(department);
    }

    public List<Department> findAll(){
        return departmentRepository.findAll();
    }

    public Department findById(int id){
        return departmentRepository
                .findById(id).
                orElseThrow(()->new DepartmentNotFoundException("Department not found "+id));
    }

    public List<Department> getDepartmentWithEmployees(){
        return departmentRepository.getDepartmentWithEmployees();
    }

    public void delete(int id){
        departmentRepository.deleteById(id);
    }


}
