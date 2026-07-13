package com.suraj.employeeapi.EmployeeApiApplication.repository;


import com.suraj.employeeapi.EmployeeApiApplication.dto.EmployeeSummaryDTO;
import com.suraj.employeeapi.EmployeeApiApplication.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface EmployeeRepository
        extends JpaRepository<Employee,Integer>{



    List<Employee> findByName(String name);
    List<Employee> findByEmail(String email);
    List<Employee> findBySalary(double salary);
    List<Employee> findByNameAndSalary(String name , double Salary);
    List<Employee> findByNameOrSalary(String name , double Salary);


    List<Employee> findByDepartment_NameAndSalary(String departmentName, double salary);

    List<Employee> findByDepartment_NameOrSalary(String departmentName, double salary);


    List<Employee> findByOrderByNameAsc();


    @Query("SELECT e FROM Employee e WHERE e.department.name = :departmentName")
    List<Employee> findEmployeeByDepartment(@Param("departmentName") String departmentName);





    List<Employee> findByNameContaining(String keyword);

    List<Employee> findByNameStartingWith(String prefix);

    List<Employee> findByNameEndingWith(String suffix);

    List<Employee> findByNameLike(String pattern);

    List<Employee> findByNameIgnoreCase(String name);

    List<Employee> findByNameContainingIgnoreCase(String keyword);




    List<Employee> findBySalaryGreaterThan(double salary);

    List<Employee> findBySalaryGreaterThanEqual(double salary);

    List<Employee> findBySalaryLessThan(double salary);

    List<Employee> findBySalaryLessThanEqual(double salary);

    List<Employee> findBySalaryBetween(double min, double max);

    List<Employee> findByEmailIsNull();

    List<Employee> findByEmailIsNotNull();






//
//    List<Employee> findByJoiningDateBefore(LocalDate date);
//
//    List<Employee> findByJoiningDateAfter(LocalDate date);
//
//    List<Employee> findByJoiningDateBetween(LocalDate start, LocalDate end);
//
//    List<Employee> findByActiveTrue();
//
//    List<Employee> findByActiveFalse();




    List<Employee> findByOrderBySalaryAsc();

    List<Employee> findByOrderBySalaryDesc();

    List<Employee> findTop5ByOrderBySalaryDesc();

    Optional<Employee> findFirstByOrderBySalaryDesc();

    Optional<Employee> findFirstByOrderBySalaryAsc();

    List<Employee> findDistinctByDepartment_Name(String departmentName);


//    Nested Property Queries & Relationship Queries start

    List<Employee> findByDepartment_NameIgnoreCase(String name);

    List<Employee> findByDepartment_Id(int id);
    List<Employee> findByDepartment_Name(String name);

    List<Employee> findByDepartment_NameContaining(String keyword);

    List<Employee> findByDepartment_NameOrderBySalaryDesc(String department);

    List<Employee> findTop3ByDepartment_NameOrderBySalaryDesc(String department);

    List<Employee> findByDepartment_NameAndSalaryGreaterThan(
            String department,
            double salary
    );


    List<Employee> findTop5ByDepartment_NameOrderBySalaryDesc(
            String department
    );


    boolean existsByEmail(String email);




//    ============================JPQL QUERIES=====================================

    @Query("""
        SELECT e
        FROM Employee e
    """)
    List<Employee> getAllEmployeesJPQL();


    @Query("""
        SELECT e
        FROM Employee e
        WHERE e.salary > 50000
    """)
    List<Employee> getEmployeesHavingSalaryGreaterThan50k();


    @Query("""
        SELECT e
        FROM Employee e
        WHERE e.department.name = 'IT'
    """)
    List<Employee> getITEmployees();



//    ==================PROJECTION===================================================


    @Query("""
    SELECT NEW
    com.suraj.employeeapi.EmployeeApiApplication.dto.EmployeeSummaryDTO(
    e.name,
    e.email,
    e.salary
    )
    FROM Employee e
    
""")
    public List<EmployeeSummaryDTO> getEmployeeSummary();




}