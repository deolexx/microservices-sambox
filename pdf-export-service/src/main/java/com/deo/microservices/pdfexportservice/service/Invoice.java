package com.deo.microservices.pdfexportservice.service;

import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class Invoice {

    Date created = new Date();
    String totalPrice = "$ 1,279.25";
    List<String> services = List.of("Refill truck", "Replace 2 tires");
    List<String> comments = List.of("Hurry up next time because i will call your boss and shout at him all the time that i have to wait for you lazy ass you lazy mother fucker son of a bitch", "Great service");

}
