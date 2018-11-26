package org.vargattila;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value="/v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {

    @Autowired
    LicenseRepository licenseRepository;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    OragnizationFeignService oragnizationFeignService;

    public LicenseServiceController(){
    }

    @RequestMapping(value="{licenseId}", method = RequestMethod.GET)
    public License getLicences(@PathVariable("licenseId") String licenseId, @PathVariable("organizationId") String organizationId){

        Optional<License> license1 = licenseRepository.findById(licenseId);
        //Organization organization = organizationService.getOrganizationById(organizationId);
        Organization organization = oragnizationFeignService.getById(organizationId);

        License license = license1.orElse(null);
        license.setOrganizationId(organization.getId());
        license.setOrganizationName(organization.getName());
        license.setContactEmail(organization.getContactEmail());
        license.setContactName(organization.getContactName());
        license.setContactPhone(organization.getContactPhone());

        return license;
    }



}
