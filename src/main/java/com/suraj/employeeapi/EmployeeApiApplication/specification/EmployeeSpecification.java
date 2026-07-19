package com.suraj.employeeapi.EmployeeApiApplication.specification;

import com.suraj.employeeapi.EmployeeApiApplication.model.Employee;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {

    public static Specification<Employee> hasName(String name){

        return (root, query, cb)->

            cb.equal(
                    root.get("name"),
                    name
            );
    }
}
