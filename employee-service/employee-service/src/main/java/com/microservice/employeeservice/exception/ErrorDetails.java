package com.microservice.employeeservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorDetails {
    private LocalDateTime timestamp;
    private String status;
    private String error;
    private String path;
}
