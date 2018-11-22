package org.vargattila;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@RepositoryRestResource(collectionResourceRel = "organization", path = "org")
public interface OrganizationRepository extends JpaRepository<Organization, String> {
}
