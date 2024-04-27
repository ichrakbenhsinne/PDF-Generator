package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.DTOs.ReservationDTO;

@Service
public interface TicketGeneratorService {


    byte[] generateParkingTicket(ReservationDTO parkingDetails);
    
}
