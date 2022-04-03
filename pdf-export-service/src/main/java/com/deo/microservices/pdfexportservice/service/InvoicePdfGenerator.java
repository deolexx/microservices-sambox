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
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Slf4j
@Component

public class InvoicePdfGenerator {

    private Style noBorder;
    private Color greenText;
    private Color grayText;
    private PdfFont futura;
    private PdfFont futuraBold;

    private void createAll() {
        try {
            noBorder = new Style().setBorder(Border.NO_BORDER);
            greenText = new DeviceRgb(0, 204, 102);
            grayText = new DeviceRgb(116, 123, 131);
            futura = PdfFontFactory.createFont("./pdf-export-service/src/main/resources/fonts/Futura Round Medium.ttf");
            futuraBold = PdfFontFactory.createFont("./pdf-export-service/src/main/resources/fonts/Futura Round Bold.ttf");
        } catch (IOException e) {
            log.error("Error filling PDF title");
        }
    }

    @SneakyThrows
    public ByteArrayOutputStream generatePdf() {
        ByteArrayOutputStream fileOutputStream = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(fileOutputStream);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        createAll();
        fillTitle(document);
        fillPrice(document);
        fillProvider(document);
        fillJobDetails(document);
        fillLaborAndService(document);
        fillComments(document);

        document.flush();
        document.close();

        return fileOutputStream;
    }

    private void fillTitle(Document document) {
        try {
            Image logo = new Image(ImageDataFactory.create("./pdf-export-service/src/main/resources/images/logo1.png"));
            PdfFont futuraBold = PdfFontFactory.createFont("./pdf-export-service/src/main/resources/fonts/Futura Round Bold.ttf");
            Paragraph title = new Paragraph("Truckup").setFont(futuraBold).setFontSize(14).add(logo.setHeight(20).setWidth(15));
            title.setBorderBottom(new SolidBorder(ColorConstants.LIGHT_GRAY, 0.01f, 0.5f));
            document.add(title);
        } catch (IOException e) {
            log.error("Error filling PDF title");
        }
    }

    //TODO Дописать когда будут входные данные, продумать создание таблицы через циклы
    private void fillPrice(Document document) {
        Table tablePrice = new Table(UnitValue.createPercentArray(new float[]{20, 25, 25, 30}));
        tablePrice
                .addCell((new Cell().addStyle(noBorder).setFont(futuraBold).setFontSize(10).add(new Paragraph("PRICE")
                        .setFontColor(greenText))))
                .addCell((new Cell().addStyle(noBorder).setFont(futuraBold).setFontSize(9).add(new Paragraph(""))))
                .addCell((new Cell().addStyle(noBorder).setFont(futuraBold).setFontSize(10).add(new Paragraph("Price summary")
                        .setFontColor(greenText))))
                .addCell((new Cell().addStyle(noBorder).setFont(futuraBold).setFontSize(9).add(new Paragraph(""))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("Total amount due")
                        .setFontColor(grayText)
                        .setFontSize(9))))
                .addCell((new Cell().addStyle(noBorder).setFont(futuraBold).setFontSize(9).add(new Paragraph(""))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("Labor"))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("$20.00")
                        .setTextAlignment(TextAlignment.RIGHT))))
                .startNewRow()
                .addCell((new Cell(3, 2).addStyle(noBorder).setFont(futuraBold).setFontSize(36).add(new Paragraph("$300 055.00"))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("Parts"))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("$300 000.00")
                        .setTextAlignment(TextAlignment.RIGHT))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("Call-out(travel)"))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("$10.00")
                        .setTextAlignment(TextAlignment.RIGHT))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("Fuel"))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("$25.00")
                        .setTextAlignment(TextAlignment.RIGHT)))).useAllAvailableWidth();

        tablePrice.setBorderBottom(new SolidBorder(grayText, 0.01f, 0.5f));

        document
                .add(tablePrice);
    }

    private void fillProvider(Document document) {

        Table tableProvider = new Table(UnitValue.createPercentArray(new float[]{20, 25, 25, 30}));
        tableProvider
                .addCell((new Cell().addStyle(noBorder).setFont(futuraBold).setFontSize(10).add(new Paragraph("Provider")
                        .setFontColor(greenText))))
                .addCell((new Cell().addStyle(noBorder).setFont(futuraBold).setFontSize(9).add(new Paragraph(""))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("Booked on")
                        .setFontColor(grayText).setFontSize(9))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("Job number")
                        .setFontColor(grayText).setFontSize(9))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("MAXIM"))))
                .addCell((new Cell().addStyle(noBorder).setFont(futuraBold).setFontSize(9).add(new Paragraph(""))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("February 25, 2022"))))
                .addCell((new Cell().addStyle(noBorder).setFont(futuraBold).setFontSize(9).add(new Paragraph("000287"))))
                .useAllAvailableWidth();

        tableProvider.setBorderBottom(new SolidBorder(grayText, 0.01f, 0.5f));
        document.add(tableProvider);
    }

    private void fillJobDetails(Document document) {

        Table tableJobDetails = new Table(UnitValue.createPercentArray(new float[]{20, 25, 25, 30}));
        tableJobDetails
                .addCell((new Cell().addStyle(noBorder).setFont(futuraBold).setFontSize(10).add(new Paragraph("JOB DETAILS")
                        .setFontColor(greenText))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph(""))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph(""))))
                .addCell((new Cell().addStyle(noBorder).setFont(futuraBold).setFontSize(9).add(new Paragraph(""))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("Company")
                        .setFontColor(grayText).setFontSize(9))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("Dispatcher")
                        .setFontColor(grayText).setFontSize(9))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("Driver")
                        .setFontColor(grayText).setFontSize(9))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("Reference number")
                        .setFontColor(grayText).setFontSize(9))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("MaxCorp"))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("John Deer"))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("Maxim Golikov"))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("+7 918 777 77 77"))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("Vehicle")
                        .setFontColor(grayText).setFontSize(9))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("VIN / Serial number")
                        .setFontColor(grayText).setFontSize(9))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("Unit number")
                        .setFontColor(grayText).setFontSize(9))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("Vehicle type")
                        .setFontColor(grayText).setFontSize(9))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("Mazzeratti"))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("777777"))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("777777"))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("Super Car"))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("Vehicle location")
                        .setFontColor(grayText).setFontSize(9))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(futuraBold).setFontSize(10)
                        .add(new Paragraph("Winner's Prospect 7a, Minsk, Belarus"))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("Customer notes")
                        .setFontColor(grayText).setFontSize(9))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("3456789"))))
                .useAllAvailableWidth();

        tableJobDetails.setBorderBottom(new SolidBorder(grayText, 0.01f, 0.5f));

        document.add(tableJobDetails);
    }

    private void fillLaborAndService(Document document) {
        Table tableLaborAndService = new Table(UnitValue.createPercentArray(new float[]{20, 25, 25, 30}));
        tableLaborAndService
                .addCell((new Cell().addStyle(noBorder).setFont(futuraBold).setFontSize(10).add(new Paragraph("LABOR")
                        .setFontColor(greenText))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph(""))))
                .addCell((new Cell().addStyle(noBorder).setFont(futuraBold).setFontSize(9).add(new Paragraph(""))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph(""))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9)
                        .add(new Paragraph("Tire repair / replacement"))))
                .addCell((new Cell().addStyle(noBorder).setFont(futuraBold).setFontSize(9).add(new Paragraph(""))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("2 @ $10.00")
                        .setFontColor(grayText).setTextAlignment(TextAlignment.RIGHT))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("$20.00")
                        .setTextAlignment(TextAlignment.RIGHT))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9)
                        .add(new Paragraph("Hourly - 2 hour minimum")
                                .setFontColor(grayText).setFontSize(10))))
                .addCell((new Cell().addStyle(noBorder).setFont(futuraBold).setFontSize(9).add(new Paragraph(""))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph(""))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph(""))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(futuraBold).setFontSize(10).add(new Paragraph("PARTS")
                        .setFontColor(greenText))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph(""))))
                .addCell((new Cell().addStyle(noBorder).setFont(futuraBold).setFontSize(9).add(new Paragraph(""))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph(""))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("Engine"))))
                .addCell((new Cell().addStyle(noBorder).setFont(futuraBold).setFontSize(9).add(new Paragraph(""))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("1 @ $300 000.00")
                        .setFontColor(grayText).setTextAlignment(TextAlignment.RIGHT))))
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("$300 000.00")
                        .setTextAlignment(TextAlignment.RIGHT)))).useAllAvailableWidth();

        tableLaborAndService.setBorderBottom(new SolidBorder(grayText, 0.01f, 0.5f));

        document.add(tableLaborAndService);
    }

    private void fillComments(Document document) {
        Table tableComments = new Table(UnitValue.createPercentArray(new float[]{20, 25, 25, 30}));
        tableComments
                .addCell((new Cell().addStyle(noBorder).setFont(futuraBold).setFontSize(10).add(new Paragraph("Comments").
                        setFontColor(greenText))))
                .startNewRow()
                .addCell((new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("shit"))))
                .useAllAvailableWidth()
                .startNewRow()
                .addCell(new Cell().addStyle(noBorder).setFont(futura).setFontSize(9).add(new Paragraph("hello darkness my old friend")))
                .useAllAvailableWidth();
        document.add(tableComments);
    }

}
