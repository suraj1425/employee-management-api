package com.suraj.employeeapi.EmployeeApiApplication.repository;

import com.suraj.employeeapi.EmployeeApiApplication.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {



    @Query( "select d from Department d JOIN FETCH d.employees" )
    List<Department> getDepartmentWithEmployees();
}
