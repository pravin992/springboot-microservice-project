package com.microservice.departmentservice.exception.specific;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(String resourceName, String fieldName, Long fieldValue){
        super(String.format("%s with %s == %s Not Found", resourceName,fieldName,fieldValue));
    }

}
