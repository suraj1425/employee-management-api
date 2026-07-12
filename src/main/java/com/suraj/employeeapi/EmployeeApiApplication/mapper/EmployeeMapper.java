package com.suraj.employeeapi.EmployeeApiApplication.mapper;

import com.suraj.employeeapi.EmployeeApiApplication.dto.EmployeeRequestDTO;
import com.suraj.employeeapi.EmployeeApiApplication.dto.EmployeeResponseDTO;
import com.suraj.employeeapi.EmployeeApiApplication.model.Department;
import com.suraj.employeeapi.EmployeeApiApplication.model.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    private ModelMapper modelMapper;


    public EmployeeMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public Employee toEntity(EmployeeRequestDTO dto , Department department){

        Employee employee = modelMapper.map(dto , Employee.class);
        employee.setDepartment(department);

        return employee;

    }


    public EmployeeResponseDTO toDTO(Employee employee){

        return modelMapper.map(employee , EmployeeResponseDTO.class);
    }



    public Employee updateEntity(Employee employee, EmployeeRequestDTO dto, Department department){


        modelMapper.map(dto , employee);

        employee.setDepartment(department);

        return employee;

    }

}
