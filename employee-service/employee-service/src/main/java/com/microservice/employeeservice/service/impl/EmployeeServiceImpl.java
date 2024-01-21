package com.microservice.employeeservice.service.impl;

import com.microservice.employeeservice.dto.DepartmentDTO;
import com.microservice.employeeservice.dto.EmployeeDTO;
import com.microservice.employeeservice.dto.EmployeeResponseDTO;
import com.microservice.employeeservice.entity.Employee;
import com.microservice.employeeservice.exception.ResourceNotFoundException;
import com.microservice.employeeservice.mapper.EmployeeMapper;
import com.microservice.employeeservice.repository.EmployeeRepository;
import com.microservice.employeeservice.service.EmployeeService;
import com.microservice.employeeservice.service.OpenFeignAPIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    WebClient webClient;
    @Autowired
    OpenFeignAPIClient openFeignAPIClient;
    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.MAPPER.mapToEmployee(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.MAPPER.mapToEmployeeDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        return saveEmployee(employeeDTO);
    }

    @Override
    public EmployeeResponseDTO getEmployee(Long id) {
        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
        Employee existingEmployeeById = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Employee with id %s not found",id)));

        // REST Call using RestTemplate
        //ResponseEntity<DepartmentDTO> departmentDTO = restTemplate.getForEntity("http://localhost:8080/v1/api/department/getDepartmentById/"+existingEmployeeById.getDepartmentCode(), DepartmentDTO.class);

        // REST call using WebClient
        /*DepartmentDTO departmentDTO = webClient.get()
                .uri("http://localhost:8080/v1/api/department/getDepartmentById/"+existingEmployeeById.getDepartmentCode())
                        .retrieve()
                                .bodyToMono(DepartmentDTO.class)
                                        .block();*/

        //REST call using openFeign
        DepartmentDTO departmentDTO = openFeignAPIClient.getDepartmentById(Long.valueOf(existingEmployeeById.getDepartmentCode()));

        System.out.println("DepartmentDTO :"+departmentDTO.toString());
        employeeResponseDTO.setDepartmentDTO(departmentDTO);
        employeeResponseDTO.setEmployeeDTO(EmployeeMapper.MAPPER.mapToEmployeeDTO(existingEmployeeById));
        return employeeResponseDTO;
    }

    public DepartmentDTO createDepartmentFromEmployeeAPI(DepartmentDTO departmentDTO){
        DepartmentDTO departmentDTO1 = restTemplate.postForObject("http://localhost:8080/v1/api/department/saveDepartment", departmentDTO, DepartmentDTO.class);
        return departmentDTO1;
    }
    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
