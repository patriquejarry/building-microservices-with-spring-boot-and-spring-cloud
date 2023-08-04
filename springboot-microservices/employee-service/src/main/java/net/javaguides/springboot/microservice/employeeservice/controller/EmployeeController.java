package net.javaguides.springboot.microservice.employeeservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.javaguides.springboot.microservice.employeeservice.dto.APIResponseDto;
import net.javaguides.springboot.microservice.employeeservice.dto.EmployeeDto;
import net.javaguides.springboot.microservice.employeeservice.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Employee Service - EmployeeController",
        description = "Employee Controller exposes REST APIs for Employee-Service"
)
@RestController
@RequestMapping(path = "/api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @Operation(
            summary = "Save Employee REST API",
            description = "Save Employee REST API is used to save employee object in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<APIResponseDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {

        APIResponseDto savedEmployeeDto = employeeService.saveEmployee(employeeDto);

        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Employee REST API",
            description = "Get Employee REST API is used to get employee object from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("/{employee-id}")
    public ResponseEntity<APIResponseDto> findEmployee(@PathVariable("employee-id") Long employeeId) {

        APIResponseDto employeeDto = employeeService.findEmployee(employeeId);

        return ResponseEntity.ok(employeeDto);
    }
}
