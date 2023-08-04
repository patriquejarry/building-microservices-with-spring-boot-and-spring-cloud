package net.javaguides.springboot.microservice.employeeservice;

import net.javaguides.springboot.microservice.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("DEPARTMENT-SERVICE")
//@FeignClient(name = "departments", url = "http://localhost:8080/api/departments")
public interface DepartmentClient {
    @GetMapping("/api/departments/{department-code}")
    DepartmentDto findDepartment(@PathVariable("department-code") String departmentCode);
}
