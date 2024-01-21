package com.microservice.departmentservice.exception.global;

import com.microservice.departmentservice.exception.ErrorDetails;
import com.microservice.departmentservice.exception.specific.DepartmentNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleDepartmentNotFoundException(DepartmentNotFoundException departmentNotFound , WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), departmentNotFound.getMessage(), HttpStatus.NOT_FOUND.name(),webRequest.getDescription(true));
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleDepartmentNotFoundException(Exception exception , WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.name(),webRequest.getDescription(true));
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> fieldError = new HashMap();
        ex.getBindingResult().getAllErrors()
                .forEach( error -> {
                    fieldError.put( ((FieldError)error).getField(), error.getDefaultMessage());
                });
        return new ResponseEntity<>(fieldError,HttpStatus.BAD_REQUEST);
    }
}
