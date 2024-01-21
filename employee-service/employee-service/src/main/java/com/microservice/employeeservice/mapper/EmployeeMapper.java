package com.microservice.employeeservice.mapper;

import com.microservice.employeeservice.dto.EmployeeDTO;
import com.microservice.employeeservice.dto.EmployeeResponseDTO;
import com.microservice.employeeservice.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper MAPPER = Mappers.getMapper(EmployeeMapper.class);
    @Mappings({
            @Mapping(target = "emp_FirstName", source = "firstName"),
            @Mapping(target = "emp_LastName", source = "lastName"),
            @Mapping(target = "emp_Email", source = "email")})
    public EmployeeDTO mapToEmployeeDTO(Employee employee);

    @Mappings({
            @Mapping(source = "emp_FirstName", target = "firstName"),
            @Mapping(source = "emp_LastName", target = "lastName"),
            @Mapping(source = "emp_Email", target = "email"),
            @Mapping(source = "departmentCode", target = "departmentCode"),
    })
    public Employee mapToEmployee(EmployeeDTO employee);
}
