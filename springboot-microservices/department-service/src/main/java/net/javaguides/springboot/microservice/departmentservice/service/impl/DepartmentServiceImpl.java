package net.javaguides.springboot.microservice.departmentservice.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.microservice.departmentservice.dto.DepartmentDto;
import net.javaguides.springboot.microservice.departmentservice.entity.Department;
import net.javaguides.springboot.microservice.departmentservice.mapper.DepartmentMapper;
import net.javaguides.springboot.microservice.departmentservice.repository.DepartmentRepository;
import net.javaguides.springboot.microservice.departmentservice.service.DepartmentService;
import net.javaguides.springboot.microservice.departmentservice.exception.CodeAlreadyExistsException;
import net.javaguides.springboot.microservice.departmentservice.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepository departmentRepository;

    ModelMapper modelMapper;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

        if(departmentRepository.findDepartmentByCode(departmentDto.getCode()).isPresent()){
            throw new CodeAlreadyExistsException("Code '" + departmentDto.getCode() + "' already exists");
        }

        //Department department = modelMapper.map(departmentDto, Department.class);
        Department department = DepartmentMapper.INSTANCE.toDepartment(departmentDto);

        Department savedDepartment = departmentRepository.save(department);

        //return modelMapper.map(savedDepartment, DepartmentDto.class);
        return DepartmentMapper.INSTANCE.toDepartmentDto(department);
    }

    @Override
    public DepartmentDto findDepartment(String departmentCode) {

        System.out.println("findDepartment 8080");
        Department department = departmentRepository.findDepartmentByCode(departmentCode).orElseThrow(
            () -> new ResourceNotFoundException("Department", "code", departmentCode)
        );

        //return modelMapper.map(department, DepartmentDto.class);
        return DepartmentMapper.INSTANCE.toDepartmentDto(department);
    }

}
