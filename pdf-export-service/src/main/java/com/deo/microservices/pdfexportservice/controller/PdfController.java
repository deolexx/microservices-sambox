package com.deo.microservices.pdfexportservice.controller;

import com.deo.microservices.pdfexportservice.service.Invoice;
import com.deo.microservices.pdfexportservice.service.InvoicePdfGenerator;
import com.itextpdf.kernel.exceptions.PdfException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PdfController {

    private final InvoicePdfGenerator invoicePdfGenerator;

    @GetMapping("/pdf")
    public void downloadInvoicePdf(HttpServletRequest request, HttpServletResponse response) {

        try (OutputStream os = response.getOutputStream(); ByteArrayOutputStream baos = invoicePdfGenerator.generatePdf(new Invoice())) {
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",
                    "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());
            baos.writeTo(os);
            os.flush();
        } catch (IOException | PdfException e) {
            log.error("Error downloading PDF");
        }
    }
}