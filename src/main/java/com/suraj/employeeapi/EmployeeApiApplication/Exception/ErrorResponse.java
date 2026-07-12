package com.suraj.employeeapi.EmployeeApiApplication.Exception;

import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public class ErrorResponse {

    private int status;
    private String message;

    private List<String> errors;


    public ErrorResponse(String message, int status) {
        this.status = status;
        this.message = message;
    }

    public ErrorResponse(String message, int status ,List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }
}
