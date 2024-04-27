package com.example.demo.DTOs;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
@JsonPropertyOrder
public class ReservationDTO {
    private Long id;
    private String reservationState;
    private String owneridentifiant;
    @JsonDeserialize(using = CustomDateDeserializer.class)
     Date enterdate;
    @JsonDeserialize(using = CustomDateDeserializer.class)
     Date exitdate;
    private boolean payementState;
   
     List<PlaceInfo> listesInfo;


    // Getters et Setters pour chaque attribut


    public Long getId() {
        return id;
    }

    public Date getEnterdate() {
        return enterdate;
    }

    public void setEnterdate(Date enterdate) {
        this.enterdate = enterdate;
    }

    public Date getExitdate() {
        return exitdate;
    }

    public void setExitdate(Date exitdate) {
        this.exitdate = exitdate;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getReservationState() {
        return reservationState;
    }

  
    public void setReservationState(String reservationState) {
        this.reservationState = reservationState;
    }

    public String getOwneridentifiant() {
        return owneridentifiant;
    }

    public void setOwneridentifiant(String owneridentifiant) {
        this.owneridentifiant = owneridentifiant;
    }

  

    public boolean isPayementState() {
        return payementState;
    }

    public void setPayementState(boolean payementState) {
        this.payementState = payementState;
    }

   
  
    // Constructeurs

    public ReservationDTO() {
    }

    public ReservationDTO(Long id, String reservationState, String owneridentifiant, Date enterDate, Date exitDate, boolean payementState, List<PlaceInfo> listesInfoId) {
        this.id = id;
        this.reservationState = reservationState;
        this.owneridentifiant = owneridentifiant;
        this.enterdate = enterDate;
        this.exitdate = exitDate;
        this.payementState = payementState;
        this.listesInfo = listesInfoId;
    }

    public List<PlaceInfo> getListesInfo() {
        return listesInfo;
    }

    public void setListesInfo(List<PlaceInfo> listesInfo) {
        this.listesInfo = listesInfo;
    }

  
    
}
