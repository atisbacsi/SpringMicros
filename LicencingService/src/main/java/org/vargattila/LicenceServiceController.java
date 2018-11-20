package org.vargattila;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/v1/organizations/{organizationId}/licences")
public class LicenceServiceController {
    public LicenceServiceController(){
    }

    @RequestMapping(value="{licenceId}", method = RequestMethod.GET)
    public Licence getLicences(@PathVariable("licenceId") String licenceId, @PathVariable("organizationId") String organizationId){
        return Licence.builder()
                .id(licenceId)
                .licenceType("Seat")
                .productName("Telco")
                .organizationId(organizationId)
                .build();
    }

}
