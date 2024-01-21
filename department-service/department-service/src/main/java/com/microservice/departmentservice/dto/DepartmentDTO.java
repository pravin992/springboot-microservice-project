package com.microservice.departmentservice.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private Long id;
    @NotEmpty
    private String departmentName;
    private String departmentDescription;
    @NotNull
    private String departmentCode;
}
