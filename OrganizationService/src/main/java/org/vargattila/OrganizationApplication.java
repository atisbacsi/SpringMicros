package org.vargattila;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class OrganizationApplication {

    @Autowired
    OrganizationRepository orgRepo;

    public static void main(String... args) {
        SpringApplication.run(OrganizationApplication.class, args);
    }

    @Bean
    public CommandLineRunner createOrg1() {
        return arg -> {

            Organization org1 = new Organization();
            org1.setId("1");

            org1.setName("Ez a neve");
            org1.setContactEmail("email@email.com");
            org1.setContactName("Name");
            org1.setContactPhone("+49 0174530608");

            orgRepo.save(org1);
        };
    }

    @Bean
    public CommandLineRunner loadOrg1() {
        return arg -> {

            List<Organization> all = orgRepo.findAll();
            System.out.println(all);
        };
    }
}