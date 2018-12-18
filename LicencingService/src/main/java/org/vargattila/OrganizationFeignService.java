package org.vargattila;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("organizationservice")
public interface OrganizationFeignService {

    @RequestMapping(path = "/orgById/{id}")
    public Organization getById(@PathVariable("id") String id);
}
