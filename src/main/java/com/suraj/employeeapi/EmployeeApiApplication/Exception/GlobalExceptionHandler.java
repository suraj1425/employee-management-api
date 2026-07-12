package com.suraj.employeeapi.EmployeeApiApplication.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeNotFound(EmployeeNotFoundException ex){



        ErrorResponse error = new ErrorResponse(ex.getMessage() , 404);

        return new ResponseEntity<>(error , HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDepartmentNotFound(DepartmentNotFoundException ex){

        ErrorResponse error = new ErrorResponse(ex.getMessage() , 404);

        return new ResponseEntity<>(error , HttpStatus.NOT_FOUND);
    }


//  FOR VALIDATION CHECK HANDLER
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ErrorResponse>
handleValidationException(
        MethodArgumentNotValidException ex
){


    List<String> errors =
            ex.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList();



    ErrorResponse response =
            new ErrorResponse(
                    "Validation failed",
                    400,
                    errors
            );



    return new ResponseEntity<>(
            response,
            HttpStatus.BAD_REQUEST
    );


}



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllException(Exception ex){

        ErrorResponse error = new ErrorResponse("somthing went wronge" ,500);

        return new ResponseEntity<>(error , HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
