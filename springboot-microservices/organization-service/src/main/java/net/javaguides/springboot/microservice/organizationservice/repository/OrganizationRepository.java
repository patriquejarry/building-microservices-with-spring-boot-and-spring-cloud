package net.javaguides.springboot.microservice.organizationservice.repository;

import net.javaguides.springboot.microservice.organizationservice.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Optional<Organization> findOrganizationByCode(String code);
}
