package net.javaguides.springboot.microservice.organizationservice.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.microservice.organizationservice.dto.OrganizationDto;
import net.javaguides.springboot.microservice.organizationservice.entity.Organization;
import net.javaguides.springboot.microservice.organizationservice.mapper.OrganizationMapper;
import net.javaguides.springboot.microservice.organizationservice.repository.OrganizationRepository;
import net.javaguides.springboot.microservice.organizationservice.service.OrganizationService;
import net.javaguides.springboot.microservice.organizationservice.exception.CodeAlreadyExistsException;
import net.javaguides.springboot.microservice.organizationservice.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    OrganizationRepository organizationRepository;

    ModelMapper modelMapper;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {

        if(organizationRepository.findOrganizationByCode(organizationDto.getCode()).isPresent()){
            throw new CodeAlreadyExistsException("Code '" + organizationDto.getCode() + "' already exists");
        }

        //Organization organization = modelMapper.map(organizationDto, Organization.class);
        Organization organization = OrganizationMapper.INSTANCE.toOrganization(organizationDto);
        organization.setCreatedDate(LocalDateTime.now());

        Organization savedOrganization = organizationRepository.save(organization);

        //return modelMapper.map(savedOrganization, OrganizationDto.class);
        return OrganizationMapper.INSTANCE.OrganizationDto(organization);
    }

    @Override
    public OrganizationDto findOrganization(String organizationCode) {

        System.out.println("findOrganization 8083");
        Organization organization = organizationRepository.findOrganizationByCode(organizationCode).orElseThrow(
            () -> new ResourceNotFoundException("Organization", "code", organizationCode)
        );

        //return modelMapper.map(department, DepartmentDto.class);
        return OrganizationMapper.INSTANCE.OrganizationDto(organization);
    }

}
