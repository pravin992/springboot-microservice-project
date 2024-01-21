package com.microservice.employeeservice.service;

import com.microservice.employeeservice.dto.DepartmentDTO;
import com.microservice.employeeservice.dto.EmployeeDTO;
import com.microservice.employeeservice.dto.EmployeeResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


public interface EmployeeService {
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO);
    public EmployeeResponseDTO getEmployee(Long id);
    public void deleteEmployee(Long id);
    public DepartmentDTO createDepartmentFromEmployeeAPI(DepartmentDTO departmentDTO);
}
