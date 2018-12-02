package org.vargattila;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class OrganizationService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(
                            name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = "1000"
                    )
            },
            fallbackMethod = "getFallbackOrganization"
    )
    public Organization getOrganizationById(String id) {

        try {
            int anInt = ThreadLocalRandom.current().nextInt(0, 3);
            if (anInt > 1) {
                Thread.sleep(11000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return restTemplate.getForObject("http://organizationservice/orgById/{id}", Organization.class, id);
    }
    private Organization getFallbackOrganization(String id){
        Organization organization = new Organization();
        organization.setContactName("This is a fallback organization");
        return organization;
    }

}
