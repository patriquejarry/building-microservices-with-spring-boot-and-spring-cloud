package net.javaguides.springboot.microservice.employeeservice.service;

import net.javaguides.springboot.microservice.employeeservice.dto.APIResponseDto;
import net.javaguides.springboot.microservice.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

    public APIResponseDto saveEmployee(EmployeeDto employeeDto);

    public APIResponseDto findEmployee(Long employeeId);
}
