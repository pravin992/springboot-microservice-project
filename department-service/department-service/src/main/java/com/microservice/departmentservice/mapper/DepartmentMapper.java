package com.microservice.departmentservice.mapper;

import com.microservice.departmentservice.dto.DepartmentDTO;
import com.microservice.departmentservice.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {
    DepartmentMapper MAPPER = Mappers.getMapper(DepartmentMapper.class);
    Department mapToDepartment(DepartmentDTO departmentDTO);
    DepartmentDTO mapToDepartmentDTO(Department departmentDTO);
}
