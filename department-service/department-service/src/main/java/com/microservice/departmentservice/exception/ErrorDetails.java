package com.microservice.departmentservice.exception;

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
    private LocalDateTime localDateTime;
    private String message;
    private String errorCode;
    private String path;
}
