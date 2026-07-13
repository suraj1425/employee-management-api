package com.suraj.employeeapi.EmployeeApiApplication.controller;


import com.suraj.employeeapi.EmployeeApiApplication.dto.EmployeeRequestDTO;
import com.suraj.employeeapi.EmployeeApiApplication.dto.EmployeeResponseDTO;
import com.suraj.employeeapi.EmployeeApiApplication.dto.EmployeeSummaryDTO;
import com.suraj.employeeapi.EmployeeApiApplication.model.Employee;
import com.suraj.employeeapi.EmployeeApiApplication.service.EmployeeService;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {


    private final EmployeeService service;
    public EmployeeController(EmployeeService service){

        this.service = service;
    }



    // GET ALL EMPLOYEES
    @GetMapping
    public ResponseEntity<Page<EmployeeResponseDTO>> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
            ){



        return ResponseEntity.ok(
                service.getAllEmployees(page , size ,sortBy , direction)
        );

    }



    // GET EMPLOYEE BY ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable int id){

        EmployeeResponseDTO employee = service.getEmployeeById(id);

        return ResponseEntity.ok(employee);
    }

    @GetMapping("/name/{name}")
    public List<Employee> getEmployeeByName(@PathVariable String name){
        return service.findByName(name);
    }


    @GetMapping("/search/department/{department}")
    public ResponseEntity<List<Employee>> searchDepartment(@PathVariable String department){
        return ResponseEntity.ok(
                service.searchByDepartment(department));
    }



    // CREATE EMPLOYEE
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO > addEmployee(@Valid @RequestBody EmployeeRequestDTO dto){

        EmployeeResponseDTO savedEmployee = service.addEmployee(dto);

        return ResponseEntity.status(201).body(savedEmployee);

    }


    // UPDATE EMPLOYEE
    @PutMapping("/{id}")
    public void updateEmployee(
            @PathVariable int id,
            @RequestBody EmployeeRequestDTO employee
    ){

       service.updateEmployee(id, employee);
    }




    // DELETE EMPLOYEE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(
            @PathVariable int id
    ){

        service.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }


//    ============================================================================

    @GetMapping("/name")
    public List<EmployeeResponseDTO> sortByName(){
        return service.sortByName();
    }


    @GetMapping("/search")
    public ResponseEntity<List<Employee>> search(@RequestParam String keyword){

        return ResponseEntity.ok(
                service.searchByKeyword(keyword)
        );
    }


//    Nested Property Queries & Relationship Queries start

//    @GetMapping("/department/{name}")
//    public ResponseEntity<List<EmployeeResponseDTO>> gelDepartmentByName(@PathVariable String name){
//        return ResponseEntity.ok( service.findbyDepartmentName(name));
//    }

    @GetMapping("/department/{id}")
    public ResponseEntity<List<EmployeeResponseDTO>> gelDepartmentByName(@PathVariable int id){
        return ResponseEntity.ok( service.findbyDepartmentId(id));
    }




//================================JPQL====================================================


    @GetMapping("/jpql")
    public List<Employee> getAllEmployeeJPQL(){
        return service.getAllEmployeesJPQL();
    }

    @GetMapping("/jpql/50k")
    public List<Employee> getEmployeesHavingSalaryGreaterThan50k(){

        return service.getEmployeesHavingSalaryGreaterThan50k();
    }

    @GetMapping("/jpql/it")
    public List<Employee> getITEmployees(){

        return service.getAllEmployeesJPQL();
    }



//    ======================PROJECTION===========================================

    @GetMapping("/summary")
    public List<EmployeeSummaryDTO> getSummary(){

        return service.getSummary();

    }
}