package com.flightradar.flightradar.service.pdf;

import com.flightradar.flightradar.model.trip.flight.Flight;
import com.flightradar.flightradar.model.trip.hotel.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;

import static com.flightradar.flightradar.service.Static.ActualDateString;
import static com.flightradar.flightradar.service.Static.CurrentUser;
import static com.itextpdf.text.pdf.BaseFont.EMBEDDED;
import static com.itextpdf.text.pdf.BaseFont.IDENTITY_H;
import static org.eclipse.jdt.internal.compiler.util.Util.UTF_8;
import static org.thymeleaf.templatemode.TemplateMode.HTML;


@Service
@Component
public class PDFService {

    @Autowired
    private
    BarCode barCode;

    public void generateBarCode(String reservationNumber) {
        barCode.createBarCode(reservationNumber);

    }

    public Context addAtributes(String barCodeName, BigDecimal totalPrice, Flight flight, Hotel hotel) {
        Context context = new Context();
        context.setVariable("reservation", barCodeName);
        context.setVariable("flight", flight);
        context.setVariable("hotel", hotel);
        context.setVariable("totalPrice", totalPrice);
        context.setVariable("name", CurrentUser().getUser().getUsername());
        context.setVariable("date", ActualDateString());

        return context;
    }

    public void generatePdf(Context attributes, String barCodeName) throws Exception {

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(HTML);
        templateResolver.setCharacterEncoding(UTF_8);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

      /*  Context context = new Context();
        context.setVariable("reservation", barCodeName);*/

        // Flying Saucer needs XHTML - not just normal HTML. To make our life
        // easy, we use JTidy to convert the rendered Thymeleaf template to
        // XHTML. Note that this might not work for very complicated HTML. But
        // it's good enough for a simple letter.
        String renderedHtmlContent = templateEngine.process("pdf/template", attributes);
        String xHtml = convertToXhtml(renderedHtmlContent);

        ITextRenderer renderer = new ITextRenderer();
        renderer.getFontResolver().addFont("pdf/Code39.ttf", IDENTITY_H, EMBEDDED);

        //working directory
        String baseUrl = FileSystems
                .getDefault()
                .getPath("src", "main", "resources", "pdf")
                .toUri()
                .toURL()
                .toString();
        renderer.setDocumentFromString(xHtml, baseUrl);
        renderer.layout();

        // And finally, we create the PDF:


        String OUTPUT_FILE = "C:\\Users\\Krystian\\Desktop\\Flight Radar\\flight-radar\\src\\main\\resources\\pdf\\Reservation-" + barCodeName + ".pdf";
        System.out.println(OUTPUT_FILE);
        OutputStream outputStream = new FileOutputStream(OUTPUT_FILE);
        renderer.createPDF(outputStream);
        outputStream.close();
    }


    private String convertToXhtml(String html) throws UnsupportedEncodingException {
        Tidy tidy = new Tidy();
        tidy.setInputEncoding(UTF_8);
        tidy.setOutputEncoding(UTF_8);
        tidy.setXHTML(true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        tidy.parseDOM(inputStream, outputStream);
        return outputStream.toString(UTF_8);
    }

}
