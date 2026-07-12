package com.suraj.employeeapi.EmployeeApiApplication.config;


import org.modelmapper.ModelMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){

//        ModelMapper modelMapper = new ModelMapper();
//
//        TypeMap<Employee, EmployeeResponseDTO> typeMap = modelMapper
//                .createTypeMap(Employee.class, EmployeeResponseDTO.class);
//
//        typeMap.addMappings(m->
//                            m.map(
//                                    src->
//                                        src.getDepartment()
//                                        .getName(),
//                                    EmployeeResponseDTO::setDepartmentName));
//
//

        return new ModelMapper();
    }
}
