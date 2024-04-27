package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.DTOs.Fiche;
import com.example.demo.DTOs.ReservationDTO;

@Service
public interface FicheGeneratorService {

 byte[] generateMaintenanceFiche(Fiche fiche);
    
}
