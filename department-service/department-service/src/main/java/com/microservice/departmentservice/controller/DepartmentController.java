package com.microservice.departmentservice.controller;

import com.microservice.departmentservice.dto.DepartmentDTO;
import com.microservice.departmentservice.exception.specific.DepartmentNotFoundException;
import com.microservice.departmentservice.exception.ErrorDetails;
import com.microservice.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/department")
@AllArgsConstructor
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @PostMapping("/saveDepartment")
    public ResponseEntity<DepartmentDTO> saveDepartment(@RequestBody @Valid DepartmentDTO departmentDTO){
        DepartmentDTO savedDepartmentDTO = departmentService.saveDepartment(departmentDTO);
        return new ResponseEntity<DepartmentDTO>(savedDepartmentDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getDepartmentById/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id){
        DepartmentDTO savedDepartmentDTO = departmentService.getDepartmentById(id);
        return new ResponseEntity<DepartmentDTO>(savedDepartmentDTO, HttpStatus.OK);
    }

    @PutMapping("/updateDepartment/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id , @RequestBody DepartmentDTO departmentDTO){
        departmentDTO.setId(id);
        DepartmentDTO savedDepartmentDTO = departmentService.updateDepartment(departmentDTO);
        return new ResponseEntity<DepartmentDTO>(savedDepartmentDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        departmentService.deleteDepartmentById(id);
        return new ResponseEntity<>("Department Deleted", HttpStatus.OK);
    }

    // Specific Controller exception handler
    /*@ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleDepartmentNotFoundException(DepartmentNotFoundException departmentNotFound , WebRequest webRequest){
       ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), departmentNotFound.getMessage(),HttpStatus.NOT_FOUND.name(),webRequest.getDescription(true));
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }*/
}
