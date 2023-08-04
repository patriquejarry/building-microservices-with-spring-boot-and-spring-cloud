package net.javaguides.springboot.microservice.employeeservice;

import net.javaguides.springboot.microservice.employeeservice.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("ORGANIZATION-SERVICE")
//@FeignClient(name = "organizations", url = "http://localhost:8083/api/organizations")
public interface OrganizationClient {
    @GetMapping("/api/organizations/{organization-code}")
    OrganizationDto findOrganization(@PathVariable("organization-code") String organizationCode);
}
