package com.deo.microservices.pdfexportservice.service;

import lombok.Getter;

import java.util.List;

@Getter
public class Invoice {

    String totalPrice = "$ 1,279.25";
    List<String> services = List.of("Refill truck", "Replace 2 tires");
    List<String> comments = List.of("Hurry up next time", "Great service");

}
