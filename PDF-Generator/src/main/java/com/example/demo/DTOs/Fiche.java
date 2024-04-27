package com.example.demo.DTOs;

import java.util.Date;
import java.util.List;



public class Fiche {
    private String id;

    private Date maintenancedate;
    private double duration;
    private double cost;
   private List<Device> devices;
   private String employname;
   private Integer tel;


   
public Date getMaintenancedate() {
    return maintenancedate;
}
public void setMaintenancedate(Date maintenancedate) {
    this.maintenancedate = maintenancedate;
}
public double getDuration() {
    return duration;
}
public void setDuration(double duration) {
    this.duration = duration;
}
public double getCost() {
    return cost;
}
public void setCost(double cost) {
    this.cost = cost;
}
public List<Device> getDevices() {
    return devices;
}
public void setDevices(List<Device> devices) {
    this.devices = devices;
}
public String getEmployname() {
    return employname;
}
public void setEmployname(String employname) {
    this.employname = employname;
}
public Integer getTel() {
    return tel;
}
public void setTel(Integer tel) {
    this.tel = tel;
}
public Fiche() {
}
public Fiche(Date maintenancedate, double duration, double cost, List<Device> devices, String employname, Integer tel) {
    this.maintenancedate = maintenancedate;
    this.duration = duration;
    this.cost = cost;
    this.devices = devices;
    this.employname = employname;
    this.tel = tel;
}
public String getId() {
    return id;
}
public void setId(String id) {
    this.id = id;
}
    





    

    
}
