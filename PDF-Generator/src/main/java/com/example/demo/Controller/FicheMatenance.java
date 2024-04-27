package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.DTOs.Device;
import com.example.demo.DTOs.Fiche;
import com.example.demo.Service.FicheGeneratorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path = "/pdf")
public class FicheMatenance {
    
    @Autowired
    private FicheGeneratorService ficheservice;

    @RequestMapping("/generateFiche")
    public ResponseEntity<byte[]> getPDF(HttpServletRequest request, HttpServletResponse response) {
        // Create devices
        Device device1 = new Device();
        device1.setDevicetype("Air Conditioning");
        device1.setModel("ABC123");
        device1.setPower(240);
        device1.setSize("Large");
        device1.setDescription("Air conditioning unit for the main hall");
        device1.setStatus(true); // Assume that the device is in working condition
        
        Device device2 = new Device();
        device2.setDevicetype("Heater");
        device2.setModel("DEF456");
        device2.setPower(120);
        device2.setSize("Small");
        device2.setDescription("Heater for the office room");
        device2.setStatus(false); // Assume that the device needs repair

        // Create a Fiche instance
        Fiche fiche = new Fiche();
        fiche.setMaintenancedate(new Date());
        fiche.setDuration(4.0); // Assuming maintenance duration is 4 hours
        fiche.setCost(500.0); // Assuming maintenance cost is $500
        fiche.setDevices(new ArrayList<>(List.of(device1, device2)));
        fiche.setEmployname("John Doe");
        fiche.setTel(123456789); // Assuming telephone number
        fiche.setId("123"); // Assuming fiche ID
        
        // Generate maintenance fiche PDF
        byte[] pdfBytes = ficheservice.generateMaintenanceFiche(fiche);

        // Return PDF as response
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}