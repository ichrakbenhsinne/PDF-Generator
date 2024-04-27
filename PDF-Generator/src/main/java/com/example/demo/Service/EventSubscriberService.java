package com.example.demo.Service;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.converter.MessageConverter;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.example.demo.DTOs.ReservationDTO;
import com.example.demo.Exceptions.JsonParsingException;

@Service
public class EventSubscriberService {

    @Autowired
    private TicketGeneratorService serviceTicket;
    
    private static final String TOPIC = "Reservation";

    private final ObjectMapper objectMapper;
    
  public static final String ERROR_MESSAGE = "Inspection Listener : Error parsing JSON string ";
    
    public EventSubscriberService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        // Configurer l'ObjectMapper pour ignorer les propriétés inconnues
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @KafkaListener(topics = TOPIC, groupId = "group_id")
    public void consumeEvent(String data)
    
    
    {
        ReservationDTO reservationdto= extractAlertFromJson(data);
       
// Affecter les valeurs à votre objet ReservationDTO
        try {
           
            byte[] ticketBytes = serviceTicket.generateParkingTicket(reservationdto);
            
            if (ticketBytes != null && ticketBytes.length > 0) {
                System.out.println("Génération du ticket réussie");
            } else {
                System.out.println("La génération du ticket(s) a échoué");
            }
            
            // Ajoutez votre logique de traitement ici
        } catch (Exception e) {
            System.out.println("Erreur lors de la conversion du message: " + e.getMessage());
        }
    }
    
 
    public ReservationDTO extractAlertFromJson(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
          return objectMapper.readValue(jsonString, ReservationDTO.class);
        } catch (JsonProcessingException e) {
          throw new JsonParsingException(ERROR_MESSAGE, e);
        }
      }
    
    
}
