package com.example.demo.Controller;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.demo.DTOs.PlaceInfo;
import com.example.demo.DTOs.ReservationDTO;
import com.example.demo.Service.TicketGeneratorService;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path = "/pdf")
public class TicketController {
    
    @Autowired
    private TicketGeneratorService ticketGeneratorService;
    
    @RequestMapping("/generate")
    public ResponseEntity<byte[]> getPDF(HttpServletRequest request, HttpServletResponse response) {
        // Create a sample ReservationDTO instance with dummy values
 
       
 
        List<PlaceInfo> PlacesInfo = new ArrayList<>();
       
    
        // Create a sample ReservationDTO instance with dummy values
        ReservationDTO reservation = new ReservationDTO(
            3L, // id
            "Confirmed", // reservationState
            "JohnDoe", // owneridentifiant
            new Date(), // enterDate
            new Date(), // exitDate
            true, // payementState
            PlacesInfo // listesInfoId
        );
       
       reservation.setOwneridentifiant("12345");
       reservation.setEnterdate(new Date()); // Date d'entrée actuelle
       reservation.setExitdate(new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000)); // Date de sortie = Date d'entrée + 2 heures
       
     byte[] pdfBytes = ticketGeneratorService.generateParkingTicket(reservation);
        
        // Return PDF as response
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}