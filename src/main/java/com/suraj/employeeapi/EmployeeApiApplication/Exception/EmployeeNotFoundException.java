package com.suraj.employeeapi.EmployeeApiApplication.Exception;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(String msg){
        super(msg);
    }
}
