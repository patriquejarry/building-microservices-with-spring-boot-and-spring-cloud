package net.javaguides.springboot.microservice.employeeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "APIResponseDto Model Information")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class APIResponseDto {
    @Schema(description = "EmployeeDto Model Information")
    private EmployeeDto employee;
    @Schema(description = "DepartmentDto Model Information")
    private DepartmentDto department;
    @Schema(description = "OrganizationDto Model Information")
    private OrganizationDto organization;
}
