package org.vargattila;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrganizationService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand
    public Organization getOrganizationById(String id){
        return restTemplate.getForObject("http://organizationservice/orgById/{id}", Organization.class, id);
    }
}
