package net.javaguides.springboot.microservice.organizationservice.service;

import net.javaguides.springboot.microservice.organizationservice.dto.OrganizationDto;

public interface OrganizationService {

    public OrganizationDto saveOrganization(OrganizationDto organizationDto);

    public OrganizationDto findOrganization(String organizationCode);
}
