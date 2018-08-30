package com.insat.ParkingApp.Models;

import javax.persistence.*;

/**
 * Created by ASUS on 04/04/2017.
 */
@Entity
@IdClass(ReservationPK.class)
public class Reservation {
    private long idReservation;
    private String date;
    private Integer duree;
    private int userIdUser;
    private int parkingIdParking;

    @Id
    @Column(name = "idReservation")
    public long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(long idReservation) {
        this.idReservation = idReservation;
    }

    @Basic
    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "duree")
    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    @Id
    @Column(name = "User_idUser")
    public int getUserIdUser() {
        return userIdUser;
    }

    public void setUserIdUser(int userIdUser) {
        this.userIdUser = userIdUser;
    }

    @Id
    @Column(name = "Parking_idParking")
    public int getParkingIdParking() {
        return parkingIdParking;
    }

    public void setParkingIdParking(int parkingIdParking) {
        this.parkingIdParking = parkingIdParking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (idReservation != that.idReservation) return false;
        if (userIdUser != that.userIdUser) return false;
        if (parkingIdParking != that.parkingIdParking) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (duree != null ? !duree.equals(that.duree) : that.duree != null) return false;

        return true;
    }

    /*@Override
    public int hashCode() {
        int result = idReservation;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (duree != null ? duree.hashCode() : 0);
        result = 31 * result + userIdUser;
        result = 31 * result + parkingIdParking;
        return result;
    }*/
}
