package org.vargattila;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
public class OrganizationService {

    Logger logger = LoggerFactory.getLogger(OrganizationService.class);
    @Autowired
    OrganizationRepository organizationRepository;

    @RequestMapping(path = "/orgById/{id}")
    public Organization getById(@PathVariable("id") String id) {

        logger.debug("Ez itt a correlation_id: {}", UserContextHolder.getContext().getCorrelationId());
//        Integer randomInt = ThreadLocalRandom.current().nextInt(1, 4);
//        if (randomInt==3) {
//            try {
//                Thread.sleep(11000);
//            } catch(InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        return organizationRepository.findById(id).orElse(null);
    }
}
