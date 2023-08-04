package net.javaguides.springboot.microservice.employeeservice.mapper;

import net.javaguides.springboot.microservice.employeeservice.dto.EmployeeDto;
import net.javaguides.springboot.microservice.employeeservice.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDto toEmployeeDto(Employee employee);
    Employee toEmployee(EmployeeDto employeeDto);

}
