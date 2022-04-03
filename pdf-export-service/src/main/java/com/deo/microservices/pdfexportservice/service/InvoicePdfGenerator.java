package com.deo.microservices.pdfexportservice.service;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.properties.VerticalAlignment;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component

public class InvoicePdfGenerator {

    private Style noBorder;
    private Color greenText;
    private Color grayText;
    private PdfFont futuraBold;
    private PdfFont roboto;
    private PdfFont robotoBold;

    private void createFontsAndColors() {
        try {
//            noBorder = new Style().setBorder(new SolidBorder(new DeviceRgb(116, 123, 131), 0.01f, 0.5f));
            noBorder = new Style().setBorder(Border.NO_BORDER);
            greenText = new DeviceRgb(0, 204, 102);
            grayText = new DeviceRgb(116, 123, 131);
            futuraBold = PdfFontFactory.createFont("./pdf-export-service/src/main/resources/fonts/Futura Round Bold.ttf");
            roboto = PdfFontFactory.createFont("./pdf-export-service/src/main/resources/fonts/Roboto Medium.ttf");
            robotoBold = PdfFontFactory.createFont("./pdf-export-service/src/main/resources/fonts/Roboto Bold.ttf");
        } catch (IOException e) {
            log.error("Error filling PDF title");
        }
    }

    @SneakyThrows
    public ByteArrayOutputStream generatePdf(Invoice invoice) {
        ByteArrayOutputStream fileOutputStream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(fileOutputStream);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        createFontsAndColors();
        fillTitle(document);
        fillPrice(document, invoice);
        fillProvider(document, invoice);
        fillJobDetails(document, invoice);
        fillLaborAndService(document, invoice);
        fillComments(document, invoice);

        document.flush();
        document.close();

        return fileOutputStream;
    }

    private void fillTitle(Document document) {
        try {
            Image logo = new Image(ImageDataFactory.create("./pdf-export-service/src/main/resources/images/logo1.png"));
            Paragraph title = new Paragraph("Truckup").setFont(futuraBold).setFontSize(14).add(logo.setHeight(20).setWidth(15));
            Paragraph spacing = new Paragraph().add(new Text(""));
            spacing.setBorderBottom(new SolidBorder(ColorConstants.LIGHT_GRAY, 0.01f, 0.5f));
            document.add(title);
            document.add(spacing);
        } catch (IOException e) {
            log.error("Error filling PDF title");
        }
    }

    //TODO Дописать когда будут входные данные, продумать создание таблицы через циклы
    private void fillPrice(Document document, Invoice invoice) {
        Table tablePrice = new Table(UnitValue.createPercentArray(new float[]{20, 25, 25, 30}));
        tablePrice
                .addCell((new Cell().addStyle(noBorder).setFont(robotoBold).setFontSize(10).add(new Paragraph("PRICE")
                        .setFontColor(greenText))))
                .addCell((new Cell().addStyle(noBorder).setFont(robotoBold).setFontSize(9).add(new Paragraph(""))))
                .addCell((new Cell().addStyle(noBorder).setFont(robotoBold).setFontSize(10).add(new Paragraph("PRICE SUMMARY")
                        .setFontColor(greenText))))
                .addCell((new Cell().addStyle(noBorder).setFont(robotoBold).setFontSize(9).add(new Paragraph(""))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph("Total amount due")
                        .setFontColor(grayText)
                        .setFontSize(9))))
                .addCell((new Cell().addStyle(noBorder).setFont(robotoBold).setFontSize(10).add(new Paragraph(""))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(10).add(new Paragraph("Labor"))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(10).add(new Paragraph("$20.00")
                        .setTextAlignment(TextAlignment.RIGHT))))
                .startNewRow()
                .addCell((new Cell(3, 2).addStyle(noBorder).setFont(roboto).setFontSize(36).add(new Paragraph(invoice.totalPrice))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(10).add(new Paragraph("Parts"))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(10).add(new Paragraph("$300 000.00")
                        .setTextAlignment(TextAlignment.RIGHT))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(10).add(new Paragraph("Call-out(travel)"))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(10).add(new Paragraph("$10.00")
                        .setTextAlignment(TextAlignment.RIGHT))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(10).add(new Paragraph("Fuel"))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(10).add(new Paragraph("$25.00")
                        .setTextAlignment(TextAlignment.RIGHT)))).useAllAvailableWidth()
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder)))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder)))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder)));

        tablePrice.setBorderBottom(new SolidBorder(grayText, 0.01f, 0.5f));

        document
                .add(tablePrice);
    }

    private void fillProvider(Document document, Invoice invoice) {

        Table tableProvider = new Table(UnitValue.createPercentArray(new float[]{20, 25, 25, 30}));
        tableProvider
                .addCell((new Cell().addStyle(noBorder).setFont(robotoBold).setFontSize(10).add(new Paragraph("PROVIDER")
                        .setFontColor(greenText))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph(""))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph("Booked on")
                        .setFontColor(grayText).setFontSize(9))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph("Job number")
                        .setFontColor(grayText).setFontSize(9))))
                .startNewRow()
                .addCell((new Cell(1, 2).addStyle(noBorder).setVerticalAlignment(VerticalAlignment.BOTTOM).setFont(roboto).setFontSize(20).add(new Paragraph("Kaylinn Westervelt"))))
                .addCell((new Cell().addStyle(noBorder).setVerticalAlignment(VerticalAlignment.BOTTOM).setFont(roboto).setFontSize(13).add(new Paragraph("February 25, 2022"))))
                .addCell((new Cell().addStyle(noBorder).setVerticalAlignment(VerticalAlignment.BOTTOM).setFont(robotoBold).setFontSize(13).add(new Paragraph("000287"))))
                .useAllAvailableWidth()
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder)))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder)))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder)));

        tableProvider.setBorderBottom(new SolidBorder(grayText, 0.01f, 0.5f));
        document.add(tableProvider);
    }

    private void fillJobDetails(Document document, Invoice invoice) {

        Table tableJobDetails = new Table(UnitValue.createPercentArray(new float[]{20, 25, 25, 30}));
        tableJobDetails
                .addCell((new Cell().addStyle(noBorder).setFont(robotoBold).setFontSize(10).add(new Paragraph("JOB DETAILS")
                        .setFontColor(greenText))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph("Company")
                        .setFontColor(grayText).setFontSize(9))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph("Dispatcher")
                        .setFontColor(grayText).setFontSize(9))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph("Driver")
                        .setFontColor(grayText).setFontSize(9))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph("Reference number")
                        .setFontColor(grayText).setFontSize(9))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(10).add(new Paragraph("MaxCorp"))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(10).add(new Paragraph("John Deer"))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(10).add(new Paragraph("Maxim Golikov"))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(10).add(new Paragraph("+7 918 777 77 77"))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph("Vehicle")
                        .setFontColor(grayText).setFontSize(9))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph("VIN / Serial number")
                        .setFontColor(grayText).setFontSize(9))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph("Unit number")
                        .setFontColor(grayText).setFontSize(9))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph("Vehicle type")
                        .setFontColor(grayText).setFontSize(9))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(10).add(new Paragraph("Mazzeratti"))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(10).add(new Paragraph("777777"))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(10).add(new Paragraph("777777"))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(10).add(new Paragraph("Super Car"))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph("Vehicle location")
                        .setFontColor(grayText).setFontSize(9))))
                .startNewRow()
                .addCell((new Cell(1, 4).addStyle(noBorder).setFont(robotoBold).setFontSize(12)
                        .add(new Paragraph("Winner's Prospect 7a, Minsk, Belarus"))))
                .useAllAvailableWidth()
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph("Customer notes")
                        .setFontColor(grayText))))
                .startNewRow()
                .addCell((new Cell(1, 4).addStyle(noBorder).setFont(roboto).setFontSize(10).add(new Paragraph("Some default comment with a lot of words about how the work is done"))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder)))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder)))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder)));
        tableJobDetails.setBorderBottom(new SolidBorder(grayText, 0.01f, 0.5f));
        document.add(tableJobDetails);
    }

    private void fillLaborAndService(Document document, Invoice invoice) {
        Table tableLaborAndService = new Table(UnitValue.createPercentArray(new float[]{20, 25, 25, 30}));
        tableLaborAndService
                .addCell((new Cell().addStyle(noBorder).setFont(robotoBold).setFontSize(10).add(new Paragraph("LABOR")
                        .setFontColor(greenText))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph(""))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph(""))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph(""))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9)
                        .add(new Paragraph("Tire repair / replacement"))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph(""))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph("2 @ $10.00")
                        .setFontColor(grayText).setTextAlignment(TextAlignment.RIGHT))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph("$20.00")
                        .setTextAlignment(TextAlignment.RIGHT))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9)
                        .add(new Paragraph("Hourly - 2 hour minimum")
                                .setFontColor(grayText).setFontSize(10))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph(""))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph(""))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph(""))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(robotoBold).setFontSize(10).add(new Paragraph("PARTS")
                        .setFontColor(greenText))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph(""))))
                .addCell((new Cell().addStyle(noBorder).setFont(robotoBold).setFontSize(9).add(new Paragraph(""))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph(""))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph("Engine"))))
                .addCell((new Cell().addStyle(noBorder).setFont(robotoBold).setFontSize(9).add(new Paragraph(""))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph("1 @ $300 000.00")
                        .setFontColor(grayText).setTextAlignment(TextAlignment.RIGHT))))
                .addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph("$300 000.00")
                        .setTextAlignment(TextAlignment.RIGHT)))).useAllAvailableWidth()
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder)))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder)))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder)));

        tableLaborAndService.setBorderBottom(new SolidBorder(grayText, 0.01f, 0.5f));

        document.add(tableLaborAndService);
    }

    private void fillComments(Document document, Invoice invoice) {
        Table tableComments = new Table(UnitValue.createPercentArray(new float[]{20, 25, 25, 30}));
        tableComments
                .addCell((new Cell().addStyle(noBorder).setFont(robotoBold).setFontSize(10).add(new Paragraph("COMMENTS").
                        setFontColor(greenText))));
        invoice.getComments().forEach(
                s -> tableComments.startNewRow().addCell((new Cell().addStyle(noBorder).setFont(roboto).setFontSize(9).add(new Paragraph(s))))
                        .useAllAvailableWidth());
        document.add(tableComments);
    }

}
