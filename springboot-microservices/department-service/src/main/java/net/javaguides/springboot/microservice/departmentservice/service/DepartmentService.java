package net.javaguides.springboot.microservice.departmentservice.service;

import net.javaguides.springboot.microservice.departmentservice.dto.DepartmentDto;

public interface DepartmentService {

    public DepartmentDto saveDepartment(DepartmentDto departmentDto);

    public DepartmentDto findDepartment(String departmentCode);
}
