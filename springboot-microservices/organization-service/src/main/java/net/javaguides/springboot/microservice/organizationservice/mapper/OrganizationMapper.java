package net.javaguides.springboot.microservice.organizationservice.mapper;

import net.javaguides.springboot.microservice.organizationservice.dto.OrganizationDto;
import net.javaguides.springboot.microservice.organizationservice.entity.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganizationMapper {
    OrganizationMapper INSTANCE = Mappers.getMapper(OrganizationMapper.class);

    OrganizationDto OrganizationDto(Organization organization);
    Organization toOrganization(OrganizationDto organizationDto);

}
