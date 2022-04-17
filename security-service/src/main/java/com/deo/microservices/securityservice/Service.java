package com.deo.microservices.securityservice;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

@org.springframework.stereotype.Service
public class Service {

    @PreAuthorize("hasRole('BEARER')")
    public String isBearer() {
        return "U hold the ring";
    }

    @Secured("ROLE_HOBBIT")
    public String isHobbit(){
        return "The best one";
    }

}
