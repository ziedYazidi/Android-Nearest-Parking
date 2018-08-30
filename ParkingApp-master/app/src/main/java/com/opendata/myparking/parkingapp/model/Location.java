package com.opendata.myparking.parkingapp.model;

/**
 * Created by is chan on 17/04/2016.
 */
public class Location {
    private long location_id;
    private String location_name;
    private Double cost; //Cost per hour

    public Location(){

    }
    public Location(String location_name, Double cost){
        this.location_name = location_name;
        this.cost = cost;
    }

    public void setLocation_id(long location_id){
        this.location_id = location_id;
    }

    public long getLocation_id(){
        return location_id;
    }

    public void setLocation_name(String location_name){
        this.location_name = location_name;
    }

    public String getLocation_name(){
        return location_name;
    }

    public Double getCost(){
        return cost;
    }
    public void setCost (Double cost){
        this.cost = cost;
    }
}
