package net.javaguides.springboot.microservice.departmentservice.mapper;

import net.javaguides.springboot.microservice.departmentservice.dto.DepartmentDto;
import net.javaguides.springboot.microservice.departmentservice.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    DepartmentDto toDepartmentDto(Department department);
    Department toDepartment(DepartmentDto departmentDto);

}
