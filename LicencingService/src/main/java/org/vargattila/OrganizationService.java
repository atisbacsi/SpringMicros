package org.vargattila;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrganizationService {
    @Autowired
    RestTemplate restTemplate;

    public Organization getOrganizationById(String id){
        return restTemplate.getForObject("http://organizationservice/orgById/{id}", Organization.class, id);
    }
}
