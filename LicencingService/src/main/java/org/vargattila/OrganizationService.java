package org.vargattila;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class OrganizationService {
    @Autowired
    RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(OrganizationService.class);

    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(
                            name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = "1000"
                    )
            },
            fallbackMethod = "getFallbackOrganization",
            threadPoolKey = "organizationService",
            threadPoolProperties = {
                    @HystrixProperty(
                            name = "coreSize",
                            value = "30"
                    ),
                    @HystrixProperty(
                            name = "maxQueueSize",
                            value = "10"
                    ),
            }
    )
    public Organization getOrganizationById(String id) {

        logger.info("Ez itt a correlationId:" + UserContextHolder.getContext().getCorrelationId());


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

    private Organization getFallbackOrganization(String id) {
        Organization organization = new Organization();
        organization.setContactName("This is a fallback organization");
        return organization;
    }

}
