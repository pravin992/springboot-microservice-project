package com.microservice.employeeservice.service;

import com.microservice.employeeservice.dto.DepartmentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(url = "http://localhost:8080/v1/api/department", name = "Department-Service")
public interface OpenFeignAPIClient {
    @GetMapping("/getDepartmentById/{id}")
    public DepartmentDTO getDepartmentById(@PathVariable Long id);
}
