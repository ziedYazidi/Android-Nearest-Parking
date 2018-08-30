package com.opendata.myparking.parkingapp.model;

/**
 * Created by dp on 23/04/2016.
 */
public class User {
    private long userId;
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String plate_number;
    private Double balance;

    public User(){

    }
    public User(String first_name, String last_name, Double balance, String plate_number, String username, String password){
        this.first_name = first_name;
        this.username = username;
        this.last_name = last_name;
        this.balance = balance;
        this.plate_number = plate_number;
        this.password = password;

    }
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }

}
