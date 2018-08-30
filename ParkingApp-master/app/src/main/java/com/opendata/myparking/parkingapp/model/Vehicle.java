package com.opendata.myparking.parkingapp.model;

/**
 * Created by is chan on 17/04/2016.
 */
public class Vehicle {
    long id;
    String vehicle_plate_number;
    String brand;
    String model;
    String yearManufactured; // changed to date.
    String color;


    // constructors
    public Vehicle() {

    }

    public Vehicle(String plate_number) {
        this.vehicle_plate_number = vehicle_plate_number;
    }

    public Vehicle(String vehicle_plate_number, String brand, String model, String yearManufactured, String color) {
        //this.id = id;
        this.vehicle_plate_number = vehicle_plate_number;
        this.brand = brand;
        this.model = model;
        this.yearManufactured = yearManufactured;
        this.color = color;
    }


    // setter
    public void setId(long id) {
        this.id = id;
    }

    public void setPlateNumber(String vehicle_plate_number) {
        this.vehicle_plate_number = vehicle_plate_number;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setYearManufactured(String yearManufactured){
        this.yearManufactured = yearManufactured;
    }

    public void setModel(String model) {
        this.model = model;
    }

    // getter
    public long getId() {
        return this.id;
    }

    public String getPlateNumber() {
        return this.vehicle_plate_number;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public String getModel() {
        return model;
    }

    public String getYearManufactured() {return yearManufactured;}







}
