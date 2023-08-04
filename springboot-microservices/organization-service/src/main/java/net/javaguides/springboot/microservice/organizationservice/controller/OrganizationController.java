package net.javaguides.springboot.microservice.organizationservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.javaguides.springboot.microservice.organizationservice.dto.OrganizationDto;
import net.javaguides.springboot.microservice.organizationservice.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Organization Service - OrganizationController",
        description = "Organization Controller exposes REST APIs for Organization-Service"
)@RestController
@RequestMapping(path = "/api/organizations")
@AllArgsConstructor
public class OrganizationController {

    private OrganizationService organizationService;

    @Operation(
            summary = "Save Organization REST API",
            description = "Save Organization REST API is used to save organization object in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto) {

        OrganizationDto savedOrganizationDto = organizationService.saveOrganization(organizationDto);

        return new ResponseEntity<>(savedOrganizationDto, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Organization REST API",
            description = "Get Organization REST API is used to get organization object from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("/{organization-code}")
    public ResponseEntity<OrganizationDto> findOrganization(@PathVariable("organization-code") String organizationCode) {

        OrganizationDto organizationDto = organizationService.findOrganization(organizationCode);

        return ResponseEntity.ok(organizationDto);
    }
}
