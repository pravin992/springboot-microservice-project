package com.microservice.employeeservice.controller;

import com.microservice.employeeservice.dto.DepartmentDTO;
import com.microservice.employeeservice.dto.EmployeeDTO;
import com.microservice.employeeservice.dto.EmployeeResponseDTO;
import com.microservice.employeeservice.exception.ErrorDetails;
import com.microservice.employeeservice.exception.ResourceNotFoundException;
import com.microservice.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @GetMapping("getEmployee/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployee(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.getEmployee(id), HttpStatus.OK);
    }

    @PostMapping("/createEmployee")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO){
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDTO),HttpStatus.CREATED);
    }

    @PutMapping("updateEmployee/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id , @RequestBody EmployeeDTO employeeDTO){
        employeeDTO.setId(id);
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDTO),HttpStatus.CREATED);
    }

    @DeleteMapping("deleteEmployee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee Deleted !", HttpStatus.OK);

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException,
                                                                        WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), HttpStatus.NOT_FOUND.name(),
                resourceNotFoundException.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // Rest Post testing using RestTemplate
    @PostMapping("/createDepartmentFromEmployeeAPI")
    public ResponseEntity<DepartmentDTO> createDepartmentFromEmployeeAPI(@RequestBody DepartmentDTO departmentDTO){
        return new ResponseEntity<>(employeeService.createDepartmentFromEmployeeAPI(departmentDTO),HttpStatus.CREATED);
    }
}
