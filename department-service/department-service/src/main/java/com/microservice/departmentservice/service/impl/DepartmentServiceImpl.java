package com.microservice.departmentservice.service.impl;

import com.microservice.departmentservice.dto.DepartmentDTO;
import com.microservice.departmentservice.entity.Department;
import com.microservice.departmentservice.exception.specific.DepartmentNotFoundException;
import com.microservice.departmentservice.mapper.DepartmentMapper;
import com.microservice.departmentservice.repositories.DepartmentRepository;
import com.microservice.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO deptDTO) {
        Department dept = DepartmentMapper.MAPPER.mapToDepartment(deptDTO);
        Department savedDepartment = departmentRepository.save(dept);
        return DepartmentMapper.MAPPER.mapToDepartmentDTO(savedDepartment);
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        Department byId = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department", "Id", id)); //
        DepartmentDTO fetchedDepartment = DepartmentMapper.MAPPER.mapToDepartmentDTO(byId);
        return  fetchedDepartment;
    }

    @Override
    public DepartmentDTO updateDepartment(DepartmentDTO dept) {
        Department existingDepartment = departmentRepository.getById(dept.getId());
        existingDepartment.setDepartmentName(dept.getDepartmentName());
        existingDepartment.setDepartmentCode(dept.getDepartmentCode());
        existingDepartment.setDepartmentDescription(dept.getDepartmentDescription());

        Department savedDepartment = departmentRepository.save(existingDepartment);
        return DepartmentMapper.MAPPER.mapToDepartmentDTO(savedDepartment);
    }

    @Override
    public void deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }
}
