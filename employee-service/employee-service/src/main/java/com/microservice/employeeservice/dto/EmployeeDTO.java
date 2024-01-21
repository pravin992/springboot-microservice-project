package com.microservice.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
@Data
@AllArgsConstructor
@ToString
public class EmployeeDTO {
    private Long id;
    private String emp_FirstName;
    private String emp_LastName;
    private String emp_Email;
    private String departmentCode;
}
