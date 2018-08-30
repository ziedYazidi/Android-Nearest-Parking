package com.opendata.myparking.parkingapp.model;

import java.util.Date;

/**
 * Created by is chan on 17/04/2016.
 */
public class Parking {
    private long parking_id;
    private long key_vehicle_id;
    private long key_location_id;
    private String time_in;
    private String time_out;
    private int active; //1 for true active, otherwise 0
    private Double charge;

    private  String plateNumber;

    public Parking (){

    }

    public Parking (long vehicle_id, long location_id, int active, Double charge){
        this.key_vehicle_id = vehicle_id;
        this.key_location_id = location_id;
        this.active = active;
        this.charge = charge;
        this.time_in = new Date().toString();
    }
    public Parking (long vehicle_id, long location_id, String time_in){
        this.key_vehicle_id = vehicle_id;
        this.key_location_id = location_id;
        this.charge = 0.0;
        this.active = 1;
        this.time_in = time_in;
    }
    public void setParking_id (int parking_id){
        this.parking_id = parking_id;
    }

    public long getParking_id(){

        return this.parking_id;
    }
    public void setTime_in (String time_in){

        this.time_in = time_in;
    }
    public String getTime_in (){

        return this.time_in;
    }

    public void setTime_out (String time_out){

        this.time_out = time_out;
    }
    public String getTime_out (){

        return this.time_out;
    }

    public void setKey_vehicle_id (long key_vehicle_id){
        this.key_vehicle_id = key_vehicle_id;
    }

    public long getKey_vehicle_id(){
        return key_vehicle_id;
    }

    public void setKey_location_id (long key_location_id){
        this.key_location_id = key_location_id;
    }

    public long getKey_location_id(){
        return key_location_id;
    }

    public void setCharge (Double charge){
        this.charge = charge;
    }

    public Double getCharge (){

        return this.charge;

    }
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setParking_id(long parking_id) {
        this.parking_id = parking_id;
    }

    public int getActive(){
        return active;
    }

    public void setActive (int active){
        this.active = active;
    }
}
