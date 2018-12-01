package org.vargattila;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableCircuitBreaker
public class OrganizationApplication {
    public static void main(String... args) {
        SpringApplication.run(OrganizationApplication.class, args);
    }
}