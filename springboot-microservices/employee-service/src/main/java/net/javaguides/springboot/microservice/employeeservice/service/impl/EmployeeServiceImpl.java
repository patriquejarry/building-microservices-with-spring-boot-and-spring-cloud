package net.javaguides.springboot.microservice.employeeservice.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import net.javaguides.springboot.microservice.employeeservice.DepartmentClient;
import net.javaguides.springboot.microservice.employeeservice.OrganizationClient;
import net.javaguides.springboot.microservice.employeeservice.dto.APIResponseDto;
import net.javaguides.springboot.microservice.employeeservice.dto.DepartmentDto;
import net.javaguides.springboot.microservice.employeeservice.dto.EmployeeDto;
import net.javaguides.springboot.microservice.employeeservice.dto.OrganizationDto;
import net.javaguides.springboot.microservice.employeeservice.entity.Employee;
import net.javaguides.springboot.microservice.employeeservice.exception.EmailAlreadyExistsException;
import net.javaguides.springboot.microservice.employeeservice.exception.ResourceNotFoundException;
import net.javaguides.springboot.microservice.employeeservice.mapper.EmployeeMapper;
import net.javaguides.springboot.microservice.employeeservice.repository.EmployeeRepository;
import net.javaguides.springboot.microservice.employeeservice.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
    ModelMapper modelMapper;
    EmployeeRepository employeeRepository;
    RestTemplate restTemplate;
    WebClient webClient;
    DepartmentClient departmentClient;
    OrganizationClient organizationClient;

    @Override
    public APIResponseDto saveEmployee(EmployeeDto employeeDto) {

        if(employeeRepository.findByEmail(employeeDto.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("Email '" + employeeDto.getEmail() + "' already exists");
        }

        //Employee employee = modelMapper.map(employeeDto, Employee.class);
        Employee employee = EmployeeMapper.INSTANCE.toEmployee(employeeDto);

        Employee savedEmployee = employeeRepository.save(employee);

        //return modelMapper.map(savedEmployee, EmployeeDto.class);
        //return EmployeeMapper.INSTANCE.toEmployeeDto(savedEmployee);
        return findEmployee(savedEmployee.getId());
    }

    @Retry(name = "MyRetry", fallbackMethod = "findEmployeeRetry")
    @CircuitBreaker(name = "MyCircuitBreaker", fallbackMethod = "findEmployeeCircuitBreaker")
    @Override
    public APIResponseDto findEmployee(Long employeeId) {

        LOGGER.info("Inside method findEmployee");

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "Id", employeeId)
        );

        //String url = "http://localhost:8080/api/departments/" + employee.getDepartmentCode();
        //DepartmentDto departmentDto = restTemplate.getForEntity(url, DepartmentDto.class).getBody();
        //DepartmentDto departmentDto = webClient.get().uri(url).retrieve().bodyToMono(DepartmentDto.class).block();
        DepartmentDto departmentDto = departmentClient.findDepartment(employee.getDepartmentCode());

        //String url = "http://localhost:8080/api/organizations/" + employee.getOrganizationCode();
        //OrganizationDto organizationDto = restTemplate.getForEntity(url, OrganizationDto.class).getBody();
        //OrganizationDto organizationDto = webClient.get().uri(url).retrieve().bodyToMono(OrganizationDto.class).block();
        OrganizationDto organizationDto = organizationClient.findOrganization(employee.getOrganizationCode());

        //return modelMapper.map(employee, EmployeeDto.class);
        //return EmployeeMapper.INSTANCE.toEmployeeDto(employee);
        return new APIResponseDto(EmployeeMapper.INSTANCE.toEmployeeDto(employee), departmentDto, organizationDto);
    }

    public APIResponseDto findEmployeeCircuitBreaker(Long employeeId, Exception ex) {

        LOGGER.info("Inside method findEmployeeCircuitBreaker");

        DepartmentDto departmentDto = new DepartmentDto(
                -1L,
                "R&D Department",
                "Research and Development Department",
                "RD001"
        );

        OrganizationDto organizationDto = new OrganizationDto(
                -1L,
                "ABC Organization",
                "ABC Organization Description",
                "ABC001",
                LocalDateTime.now()
        );

        return findEmployee(employeeId, ex, departmentDto, organizationDto);
    }

    public APIResponseDto findEmployeeRetry(Long employeeId, Exception ex) {

        LOGGER.info("Inside method findEmployeeRetry");

        DepartmentDto departmentDto = new DepartmentDto(
                -2L,
                "R&D Department",
                "Research and Development Department",
                "RD002"
        );

        OrganizationDto organizationDto = new OrganizationDto(
                -2L,
                "ABC Organization",
                "ABC Organization Description",
                "ABC002",
                LocalDateTime.now()
        );

        return findEmployee(employeeId, ex, departmentDto, organizationDto);
    }

    public APIResponseDto findEmployee(Long employeeId, Exception ex,
                                       DepartmentDto departmentDto,
                                       OrganizationDto organizationDto) {

        if(ex != null) {
            LOGGER.warn(ex.getMessage());
        }

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "Id", employeeId)
        );

        return new APIResponseDto(EmployeeMapper.INSTANCE.toEmployeeDto(employee), departmentDto, organizationDto);
    }


}
