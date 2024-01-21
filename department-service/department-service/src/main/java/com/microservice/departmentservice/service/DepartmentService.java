package com.microservice.departmentservice.service;

import com.microservice.departmentservice.dto.DepartmentDTO;
import com.microservice.departmentservice.entity.Department;

public interface DepartmentService {
    public DepartmentDTO saveDepartment(DepartmentDTO dept);
    public DepartmentDTO getDepartmentById(Long id);

    public DepartmentDTO updateDepartment(DepartmentDTO dept);

    public void deleteDepartmentById(Long id);
}
