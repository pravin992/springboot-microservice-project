package com.microservice.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDTO {
    private EmployeeDTO employeeDTO;
    private DepartmentDTO departmentDTO;
}
