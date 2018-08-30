package com.insat.ParkingApp.Models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by ASUS on 04/04/2017.
 */
public class ReservationPK implements Serializable {
    private int idReservation;
    private int userIdUser;
    private int parkingIdParking;

    @Column(name = "idReservation")
    @Id
    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    @Column(name = "User_idUser")
    @Id
    public int getUserIdUser() {
        return userIdUser;
    }

    public void setUserIdUser(int userIdUser) {
        this.userIdUser = userIdUser;
    }

    @Column(name = "Parking_idParking")
    @Id
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

        ReservationPK that = (ReservationPK) o;

        if (idReservation != that.idReservation) return false;
        if (userIdUser != that.userIdUser) return false;
        if (parkingIdParking != that.parkingIdParking) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idReservation;
        result = 31 * result + userIdUser;
        result = 31 * result + parkingIdParking;
        return result;
    }
}
