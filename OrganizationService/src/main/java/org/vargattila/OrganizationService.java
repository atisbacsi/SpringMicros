package org.vargattila;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    @RequestMapping(path = "/orgById/{id}")
    public Organization getById(@PathVariable("id") String id) {
        return organizationRepository.findById(id).orElse(null);
    }
}
