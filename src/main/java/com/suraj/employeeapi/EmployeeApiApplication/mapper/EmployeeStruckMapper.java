package com.suraj.employeeapi.EmployeeApiApplication.mapper;


import com.suraj.employeeapi.EmployeeApiApplication.dto.EmployeeRequestDTO;
import com.suraj.employeeapi.EmployeeApiApplication.dto.EmployeeResponseDTO;
import com.suraj.employeeapi.EmployeeApiApplication.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeStruckMapper {


    Employee toEntity(EmployeeRequestDTO dto);

    @Mapping(
            source = "department.name",
            target = "departmentName"
    )
    EmployeeResponseDTO toDTO(Employee employee);


    void updateEntity(EmployeeRequestDTO dto,
                      @MappingTarget Employee employee
    );

//    List wale method ke liye single wala method humesha chahiye JAISE KI ISS METHOD KE LIYE toDTO() HAI.
    List<EmployeeResponseDTO> toDTOList(List<Employee> employees);

}
