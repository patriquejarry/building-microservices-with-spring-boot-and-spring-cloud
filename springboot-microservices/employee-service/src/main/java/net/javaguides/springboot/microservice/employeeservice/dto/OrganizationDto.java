package net.javaguides.springboot.microservice.employeeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Schema(description = "OrganizationDto Model Information")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {
    private Long id;
    @Schema(description = "Organization Name")
    private String name;
    @Schema(description = "Organization Description")
    private String description;
    @Schema(description = "Organization Code")
    private String code;
    @Schema(description = "Organization Created Date")
    private LocalDateTime createdDate;

}
