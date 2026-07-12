package com.suraj.employeeapi.EmployeeApiApplication.Exception;

public class DepartmentNotFoundException extends RuntimeException{

    public DepartmentNotFoundException(String message){

        super(message);
    }
}
