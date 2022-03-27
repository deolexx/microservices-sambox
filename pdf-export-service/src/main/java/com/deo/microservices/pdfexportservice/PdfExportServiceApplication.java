package com.deo.microservices.pdfexportservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(title = "PDF Service API", version = "1.0", description = "Service for PDF generation from data")
)
public class PdfExportServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PdfExportServiceApplication.class, args);
    }

}
