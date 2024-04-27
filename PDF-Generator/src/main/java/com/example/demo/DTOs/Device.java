package com.example.demo.DTOs;

public class Device {
    private String devicetype;
    private String model;
    private double power;
    private String size;
    private String description;
    private boolean status;
    
    public String getDevicetype() {
        return devicetype;
    }
    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public double getPower() {
        return power;
    }
    public void setPower(double power) {
        this.power = power;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public Device() {
    }
    public Device(String devicetype, String model, double power, String size) {
        this.devicetype = devicetype;
        this.model = model;
        this.power = power;
        this.size = size;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }



    
}
