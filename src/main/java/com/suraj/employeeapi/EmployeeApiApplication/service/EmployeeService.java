package com.suraj.employeeapi.EmployeeApiApplication.service;


import com.suraj.employeeapi.EmployeeApiApplication.Exception.DepartmentNotFoundException;
import com.suraj.employeeapi.EmployeeApiApplication.Exception.EmployeeNotFoundException;
import com.suraj.employeeapi.EmployeeApiApplication.dto.EmployeeRequestDTO;
import com.suraj.employeeapi.EmployeeApiApplication.dto.EmployeeResponseDTO;

import com.suraj.employeeapi.EmployeeApiApplication.dto.EmployeeSummaryDTO;
import com.suraj.employeeapi.EmployeeApiApplication.mapper.EmployeeStruckMapper;
import com.suraj.employeeapi.EmployeeApiApplication.model.Department;
import com.suraj.employeeapi.EmployeeApiApplication.model.Employee;
import com.suraj.employeeapi.EmployeeApiApplication.repository.DepartmentRepository;
import com.suraj.employeeapi.EmployeeApiApplication.repository.EmployeeRepository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;





@Service
public class EmployeeService {


    private EmployeeResponseDTO mapToDTO(Employee employee){
        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getDepartment().getName()
        );
    }


    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handle(EmployeeNotFoundException ex){
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
//    private final ModelMapper modelMapper;
//    private final EmployeeMapper employeeMapper;

    private final EmployeeStruckMapper employeeStruckMapper;


    public EmployeeService(EmployeeRepository repository,
            DepartmentRepository departmentRepository,
//            ModelMapper modelMapper,
//            EmployeeMapper employeeMapper,
            EmployeeStruckMapper employeeStruckMapper
    ){

        this.employeeRepository = repository;
        this.departmentRepository = departmentRepository;
//        this.modelMapper = modelMapper;
//        this.employeeMapper = employeeMapper;
        this.employeeStruckMapper = employeeStruckMapper;
    }


    // GET ALL

    public Page<EmployeeResponseDTO> getAllEmployees(
            int page,
            int size,
            String sortBy,
            String direction
    ){

        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy)
                        .descending() :
                Sort.by(sortBy)
                    .ascending();


        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        sort
                );

        Page<Employee> employeePage =

                employeeRepository

                        .findAll(pageable);

        return employeePage.map(

                employeeStruckMapper::toDTO

        );

    }


    // GET BY ID
    public EmployeeResponseDTO getEmployeeById(int id){

        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException("Employee not found" +id));

        return employeeStruckMapper.toDTO(employee);
    }

    public List<EmployeeResponseDTO> getEmployeebySortSalary( ){

        Sort sort = Sort.by("salary");

        List<Employee> employees = employeeRepository.findAll(sort);

        return employeeStruckMapper.toDTOList(employees);


    }


    // CREATE
    public EmployeeResponseDTO  addEmployee(EmployeeRequestDTO dto){


            if(employeeRepository.existsByEmail(dto.getEmail())){
                throw new RuntimeException("Email already exists");
            }

        // DTO --> Entity

        Department department =
                departmentRepository.findById(
                                dto.getDepartmentId()
                        )
                        .orElseThrow(
                                ()-> new RuntimeException(
                                        "Department not found"
                                )
                        );

        Employee employee = employeeStruckMapper.toEntity(dto);
        employee.setDepartment(department);

        Employee savedEmployee = employeeRepository.save(employee);

        // Entity --> DTO

        return employeeStruckMapper.toDTO(savedEmployee);
    }



    // UPDATE
    public void updateEmployee(int id, EmployeeRequestDTO dto){

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(
                        ()->
                            new EmployeeNotFoundException("Employee not found "+id)
                );


        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() ->
                        new DepartmentNotFoundException(
                                "Department not found with id : "
                                        + dto.getDepartmentId()
                        ));

        employeeStruckMapper.updateEntity(
                dto,
                employee
        );

        employee.setDepartment(department);

        employeeRepository.save(employee);

    }


    // DELETE
    public void deleteEmployee(int id){

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->
                        new EmployeeNotFoundException(
                                "Employee not exist of this id "+id
                        ));

        employeeRepository.delete(employee);
    }

    public List<Employee> findByName(String name){
        return employeeRepository.findByName(name);
    }

    public List<Employee> findByNameAndDepartment(String departmentName,double salary){
        return employeeRepository.findByDepartment_NameAndSalary(departmentName , salary);
    }

    public List<Employee> findByNameOrDepartment(String departmentName,double salary){
        return employeeRepository.findByDepartment_NameOrSalary(departmentName , salary);
    }

    public List<Employee> searchByDepartment(String department){

        return employeeRepository.findEmployeeByDepartment(department);

    }

//        ================================================================================


    public List<EmployeeResponseDTO> sortByName(){

        Sort sort =
                Sort.by(
                        Sort.Order.asc("salary"),
                        Sort.Order.asc("name")
                );

        List<Employee> employees = employeeRepository.findAll(sort);

        return employeeStruckMapper.toDTOList(employees);
    }


    public List<Employee> searchByKeyword(String keyword){

        return employeeRepository
                .findByNameContainingIgnoreCase(keyword);

    }



//    Nested Property Queries & Relationship Queries start

    public List<EmployeeResponseDTO> findbyDepartmentName(String name){
        List<Employee> employees = employeeRepository.findByDepartment_Name(name);

        return employeeStruckMapper.toDTOList(employees);
    }

    public List<EmployeeResponseDTO> findbyDepartmentId(int id){
        List<Employee> employees = employeeRepository.findByDepartment_Id(id);

        return employeeStruckMapper.toDTOList(employees);
    }



//    ===========================JPQL=========================================

    public List<Employee> getAllEmployeesJPQL(){

        return employeeRepository.getAllEmployeesJPQL();
    }

    public List<Employee> getEmployeesHavingSalaryGreaterThan50k(){

        return employeeRepository.getEmployeesHavingSalaryGreaterThan50k();
    }

    public List<Employee> getITEmployees(){

        return employeeRepository.getAllEmployeesJPQL();
    }



//    =========================PROJECTION=================================

    public List<EmployeeSummaryDTO> getSummary(){

        return employeeRepository.getEmployeeSummary();

    }



}