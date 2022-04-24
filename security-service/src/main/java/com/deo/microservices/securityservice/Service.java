package com.deo.microservices.securityservice;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

@org.springframework.stereotype.Service

public class Service {

    @PreAuthorize("hasRole('USER')")
    public String onlyUser() {
        return "USER access only";
    }

    @PreAuthorize("hasRole('ADMIN')")
    public String onlyAdmin() {
        return "ADMIN acces only";
    }

    @Secured({"USER", "ADMIN"})
    public String userAndAdmin() {
        return "USER and ADMIN access";
    }
}
