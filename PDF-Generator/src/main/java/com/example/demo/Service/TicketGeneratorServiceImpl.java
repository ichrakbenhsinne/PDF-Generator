package com.example.demo.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.example.demo.DTOs.PlaceInfo;
import com.example.demo.DTOs.ReservationDTO;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

@Service
public class TicketGeneratorServiceImpl implements TicketGeneratorService {

    @Autowired
    private TemplateEngine templateEngine;

    @Override
public byte[] generateParkingTicket(ReservationDTO reservationDetails) {
    try {
        // Obtenez le chemin absolu du répertoire "tickets"
        String ticketsFolderPath = "src/main/resources/tickets/";
        File ticketsFolder = new File(ticketsFolderPath);
        if (!ticketsFolder.exists()) {
            boolean created = ticketsFolder.mkdirs();
            if (!created) {
                System.out.println("Échec de la création du répertoire 'tickets'");
                return null;
                // Gérer l'échec de création du répertoire
            }
        }

        ByteArrayOutputStream allTickets = new ByteArrayOutputStream();

        // Parcourez la liste de placeInfo dans la réservation
        for (PlaceInfo placeInfo : reservationDetails.getListesInfo()) {


            System.out.println(reservationDetails.getListesInfo().size());
            
            // Créez un contexte Thymeleaf et ajoutez les détails de placeInfo
            Context context = new Context();
            context.setVariable("reservation", reservationDetails);
            context.setVariable("placeInfo", placeInfo);

            // Traitez le modèle HTML avec Thymeleaf
            String htmlContent = templateEngine.process("Ticket", context);

            // Convertissez HTML en PDF
            File pdfFile = new File(ticketsFolder, "ticket_" + placeInfo.getId() + ".pdf");
            try (OutputStream outputStream = new FileOutputStream(pdfFile)) {
                ConverterProperties converterProperties = new ConverterProperties();
                converterProperties.setBaseUri("http://localhost:8080"); // Spécifiez la base URI
                HtmlConverter.convertToPdf(htmlContent, outputStream, converterProperties); // Convertissez HTML en PDF
            }

            // Lisez le contenu du fichier PDF et ajoutez-le à la liste de tickets
            byte[] pdfBytes = java.nio.file.Files.readAllBytes(pdfFile.toPath());
            allTickets.write(pdfBytes);
        }

        // Retournez tous les tickets générés sous forme de tableau de bytes
        return allTickets.toByteArray();
    } catch (Exception e) {
        e.printStackTrace();
        // Gérez l'exception de manière appropriée
        return null;
    }
}
}