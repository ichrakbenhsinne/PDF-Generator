package com.example.demo.DTOs;

import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomDeserializer implements Deserializer<ReservationDTO> {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Configuration optionnelle du désérialiseur
    }
    @Override
    public ReservationDTO deserialize(String topic, byte[] data) {
        System.out.println("test");
        try {
            if (data == null) {
                log.error("Null received at deserializing");
                return null;
            }
            // Convertir les bytes en chaîne JSON
            String json = new String(data, "UTF-8");
            // Désérialiser la chaîne JSON en objet ReservationDTO
            return objectMapper.readValue(json, ReservationDTO.class);
        } catch (Exception e) {
            // Lancer une exception de SerializationException en cas d'erreur
            throw new SerializationException("Error when deserializing byte[] to ReservationDTO", e);
        }
    }
    @Override
    public void close() {
        // Fermeture des ressources si nécessaire
    }
}
